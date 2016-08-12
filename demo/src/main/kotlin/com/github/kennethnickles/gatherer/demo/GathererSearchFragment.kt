package com.github.kennethnickles.gatherer.demo

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnCheckedChanged
import butterknife.OnClick
import butterknife.OnTextChanged
import com.github.kennethnickles.gatherer.DeckBrewClient
import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Color
import com.github.kennethnickles.gatherer.card.Format
import com.github.kennethnickles.gatherer.card.Rarity
import com.github.kennethnickles.gatherer.card.Status
import com.github.kennethnickles.gatherer.card.Supertype
import com.github.kennethnickles.gatherer.card.Type
import com.github.kennethnickles.gatherer.demo.dagger.FragmentComponent
import com.github.kennethnickles.gatherer.demo.dagger.FragmentModule
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.util.Lists
import rx.Observer
import java.util.ArrayList
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class GathererSearchFragment : Fragment(), CardSelectionListener, SearchListener {

	companion object {
		val TAG: String = GathererSearchFragment::class.java.simpleName
		val CARD_LIST_KEY: String = "card_list_key"
	}

	@Inject
	lateinit var mDeckBrewClient: DeckBrewClient

	// Type
	@BindView(R.id.type)
	lateinit var mTextViewType: TextView
	// Subtype
	@BindView(R.id.subtype)
	lateinit var mTextInputEditTextSubtype: TextInputEditText
	// Supertype
	@BindView(R.id.supertype)
	lateinit var mTextViewSupertype: TextView
	// Name
	@BindView(R.id.name)
	lateinit var mTextInputEditTextName: TextInputEditText
	// Oracle
	@BindView(R.id.oracle)
	lateinit var mTextInputEditTextOracle: TextInputEditText
	// Rarity
	@BindView(R.id.rarity)
	lateinit var mTextViewRarity: TextView
	// Set
	@BindView(R.id.set)
	lateinit var mTextInputEditTextSet: TextInputEditText
	// Multicolor
	@BindView(R.id.multicolor)
	lateinit var mSwitchMultiColor: SwitchCompat
	// Color
	@BindView(R.id.color)
	lateinit var mTextViewColor: TextView
	// Format
	@BindView(R.id.format)
	lateinit var mTextInputEditTextFormat: TextView
	// Status
	@BindView(R.id.status)
	lateinit var mTextViewStatus: TextView
	// Page
	@BindView(R.id.page)
	lateinit var mTextInputEditTextPage: TextInputEditText
	// Clear
	@BindView(R.id.clear)
	lateinit var mTextViewClear: TextView

	@BindView(R.id.view_progress_loading)
	lateinit var mProgressLoadingView: ContentLoadingProgressBar;
	@BindView(R.id.scroll_view_error)
	lateinit var mScrollViewError: ScrollView
	@BindView(R.id.text_view_error)
	lateinit var mTextViewError: TextView
	@BindView(R.id.recycler_view_cards)
	lateinit var mRecyclerView: RecyclerView

	private var mGathererRequestBuilder = GathererRequest.builder()
	private var mFragmentComponent: FragmentComponent? = null
	private var mCards: ArrayList<Card>? = null
	private var mCardAdapter: CardAdapter? = null
	private var mCardSelectionListener: CardSelectionListener? = null

	override fun onAttach(context: Context?) {
		super.onAttach(context)

		mCardSelectionListener = context as CardSelectionListener
	}

	override fun onDetach() {
		super.onDetach()

		mCardSelectionListener = null
	}

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		super.onCreateView(inflater, container, savedInstanceState)

		return inflater!!.inflate(R.layout.fragment_gatherer_search, container, false)
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		ButterKnife.bind(this, view!!);

		view.setOnTouchListener({ v, event ->
									val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
									val searchView: View = ButterKnife.findById(activity, R.id.view_search)
									imm.hideSoftInputFromWindow(searchView.windowToken, 0);
								})
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		injectSelf()

		val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
		mRecyclerView.layoutManager = layoutManager

		mCards = Lists.newArrayList();
		mCardAdapter = CardAdapter(context, mCards!!, this)
		mRecyclerView.adapter = mCardAdapter

		mProgressLoadingView.show()
		search(mGathererRequestBuilder.build())
	}

	fun injectSelf() {
		getFragmentComponent().injectGathererSearchFragment(this)
	}

	fun getFragmentComponent(): FragmentComponent {
		if (mFragmentComponent == null) {
			mFragmentComponent = (activity as GathererActivity).getActivityComponent().plus(FragmentModule(this))
		}
		return mFragmentComponent!!
	}

	override fun onSaveInstanceState(outState: Bundle?) {
		outState!!.putParcelableArrayList(CARD_LIST_KEY, mCards!!)
		super.onSaveInstanceState(outState)
	}

	override fun onCardSelected(card: Card) {
		Log.d(TAG, "onCardSelected: " + card.name)
		mCardSelectionListener!!.onCardSelected(card)
		Snackbar.make(view!!, card.name, Snackbar.LENGTH_LONG)
				.setAction(R.string.details, { Log.d(TAG, card.name) })
				.show();
	}

	override fun onSearchOpen() {
		Log.d(TAG, "onSearchOpen")
	}

	override fun onSearchClose(): Boolean {
		Log.d(TAG, "onSearchClose")
		return false
	}

	override fun onSearch(q: String?): Boolean {
		Log.d(TAG, "onSearch: " + q)
		typeahead(q!!)
		return false
	}

	override fun onSearchTextChange(q: String?): Boolean {
		Log.d(TAG, "onSearchTextChange: " + q)
		typeahead(q!!)
		return false
	}

	private fun search(request: GathererRequest) {
		mDeckBrewClient.cards(request).subscribe(object : Observer<List<Card>> {

			override fun onNext(cards: List<Card>?) {
				mCards!!.clear()
				mCards!!.addAll(cards!!)
				mCardAdapter!!.addCards(cards!!)
				mTextViewError.visibility = View.GONE
				mScrollViewError.visibility = View.GONE
				mRecyclerView.visibility = View.VISIBLE
				mProgressLoadingView.hide()
			}

			override fun onCompleted() {
			}

			override fun onError(e: Throwable?) {
				Log.e(TAG, Log.getStackTraceString(e))
				mRecyclerView.visibility = View.GONE
				mTextViewError.text = e?.cause?.message ?: Log.getStackTraceString(e)
				mTextViewError.visibility = View.VISIBLE
				mScrollViewError.visibility = View.VISIBLE
				mProgressLoadingView.hide()
			}
		})
	}

	private fun typeahead(q: String) {
		mDeckBrewClient.typeahead(q!!).subscribe(object : Observer<List<Card>> {

			override fun onNext(cards: List<Card>?) {
				mCards!!.clear()
				mCards!!.addAll(cards!!)
				mCardAdapter!!.addCards(cards!!)
				mTextViewError.visibility = View.GONE
				mScrollViewError.visibility = View.GONE
				mRecyclerView.visibility = View.VISIBLE
				mProgressLoadingView.hide()
			}

			override fun onCompleted() {
			}

			override fun onError(e: Throwable?) {
				Log.e(TAG, Log.getStackTraceString(e))
				mRecyclerView.visibility = View.GONE
				mTextViewError.text = Log.getStackTraceString(e)
				mTextViewError.visibility = View.VISIBLE
				mScrollViewError.visibility = View.VISIBLE
				mProgressLoadingView.hide()
			}
		})
	}

	private fun typesToItemsArray(): Array<CharSequence> {
		val itemsArray: MutableList<CharSequence> = ArrayList()
		for (value in Type.values()) {
			itemsArray.add(value.getName())
		}
		return itemsArray.toTypedArray()
	}

	private fun emptySelections(size: Int): BooleanArray {
		return BooleanArray(size)
	}

	private fun supertypesToItemsArray(): Array<CharSequence> {
		val selectionArray: MutableList<CharSequence> = ArrayList()
		for (value in Supertype.values()) {
			selectionArray.add(value.getName())
		}
		return selectionArray.toTypedArray()
	}

	private fun rarityToItemsArray(): Array<CharSequence> {
		val selectionArray: MutableList<CharSequence> = ArrayList()
		for (value in Rarity.values()) {
			selectionArray.add(value.getName())
		}
		return selectionArray.toTypedArray()
	}

	private fun colorToItemsArray(): Array<CharSequence> {
		val selectionArray: MutableList<CharSequence> = ArrayList()
		for (value in Color.values()) {
			selectionArray.add(value.getName())
		}
		return selectionArray.toTypedArray()
	}

	private fun formatToItemsArray(): Array<CharSequence> {
		val selectionArray: MutableList<CharSequence> = ArrayList()
		for (value in Format.values()) {
			selectionArray.add(value.getName())
		}
		return selectionArray.toTypedArray()
	}

	private fun statusToItemsArray(): Array<CharSequence> {
		val selectionArray: MutableList<CharSequence> = ArrayList()
		for (value in Status.values()) {
			selectionArray.add(value.getName())
		}
		return selectionArray.toTypedArray()
	}

	private fun launchSelectionDialog(items: Array<CharSequence>,
									  selections: BooleanArray,
									  listener: DialogInterface.OnMultiChoiceClickListener) {
		AlertDialog.Builder(activity).setMultiChoiceItems(items, selections, listener).show()
	}

	private fun launchSelectionDialog(items: Array<CharSequence>,
									  listener: DialogInterface.OnClickListener) {
		AlertDialog.Builder(activity).setItems(items, listener).show()
	}

	@OnClick(R.id.type)
	internal fun onTypeClick(view: View) {
		val items = typesToItemsArray()
		launchSelectionDialog(items, emptySelections(items.size), object : DialogInterface.OnMultiChoiceClickListener {
			override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
				val textView = view as TextView
				textView.text = Editable.Factory.getInstance().newEditable(items[which])
				val type = items[which]
				if (isChecked) {
					mGathererRequestBuilder.withType(type)
				} else {
					mGathererRequestBuilder.withoutType(type)
				}
				search(mGathererRequestBuilder.build())
			}
		})
	}

	@OnTextChanged(R.id.subtype, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
	internal fun onSubtypeInput(text: CharSequence) {
		mGathererRequestBuilder.clearSubtypes()
		if (text.isNotEmpty()) {
			mGathererRequestBuilder.withSubtype(text)
		}
		search(mGathererRequestBuilder.build())
	}

	@OnClick(R.id.supertype)
	internal fun onSupertypeClick(view: View) {
		val items = supertypesToItemsArray()
		launchSelectionDialog(items, emptySelections(items.size), object : DialogInterface.OnMultiChoiceClickListener {
			override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
				val textView = view as TextView
				textView.text = Editable.Factory.getInstance().newEditable(items[which])
				val subtype = items[which]
				if (isChecked) {
					mGathererRequestBuilder.withSubtype(subtype)
				} else {
					mGathererRequestBuilder.withoutSubtype(subtype)
				}
				search(mGathererRequestBuilder.build())
			}
		})
	}

	@OnTextChanged(R.id.name, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
	internal fun onNameInput(text: CharSequence) {
		mGathererRequestBuilder.clearName()
		if (text.isNotEmpty()) {
			mGathererRequestBuilder.withName(text)
		}
		search(mGathererRequestBuilder.build())
	}

	@OnTextChanged(R.id.oracle, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
	internal fun onOracleInput(text: CharSequence) {
		mGathererRequestBuilder.clearOracle()
		if (text.isNotEmpty()) {
			mGathererRequestBuilder.withOracleText(text)
		}
		search(mGathererRequestBuilder.build())
	}

	@OnClick(R.id.rarity)
	internal fun onRarityClick(view: View) {
		val items = rarityToItemsArray()
		launchSelectionDialog(items, emptySelections(items.size), object : DialogInterface.OnMultiChoiceClickListener {
			override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
				val textView = view as TextView
				textView.text = Editable.Factory.getInstance().newEditable(items[which])
				val rarity = items[which]
				if (isChecked) {
					mGathererRequestBuilder.withRarity(rarity)
				} else {
					mGathererRequestBuilder.withoutRarity(rarity)
				}
				search(mGathererRequestBuilder.build())
			}
		})
	}

	@OnTextChanged(R.id.set, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
	internal fun onSetInput(text: CharSequence) {
		mGathererRequestBuilder.clearSets()
		if (text.isNotEmpty()) {
			mGathererRequestBuilder.withSet(text)
		}
		search(mGathererRequestBuilder.build())
	}

	@OnClick(R.id.color)
	internal fun onColorClick(view: View) {
		val items = colorToItemsArray()
		launchSelectionDialog(items, emptySelections(items.size), object : DialogInterface.OnMultiChoiceClickListener {
			override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
				val textView = view as TextView
				textView.text = Editable.Factory.getInstance().newEditable(items[which])
				val color = items[which]
				if (isChecked) {
					mGathererRequestBuilder.withColor(color)
				} else {
					mGathererRequestBuilder.withoutColor(color)
				}
				search(mGathererRequestBuilder.build())
			}
		})
	}

	@OnClick(R.id.format)
	internal fun onFormatClick(view: View) {
		val items = formatToItemsArray()
		launchSelectionDialog(items, object : DialogInterface.OnClickListener {
			override fun onClick(dialog: DialogInterface?, which: Int) {
				val textView = view as TextView
				textView.text = Editable.Factory.getInstance().newEditable(items[which])
				val format = items[which]
				mGathererRequestBuilder.clearFormats()
				mGathererRequestBuilder.withFormat(format)
				search(mGathererRequestBuilder.build())
			}
		})
	}

	@OnClick(R.id.status)
	internal fun onStatusClick(view: View) {
		val items = statusToItemsArray()
		launchSelectionDialog(items, object : DialogInterface.OnClickListener {
			override fun onClick(dialog: DialogInterface?, which: Int) {
				val textView = view as TextView
				textView.text = Editable.Factory.getInstance().newEditable(items[which])
				val status = items[which]
				mGathererRequestBuilder.clearStatuses()
				mGathererRequestBuilder.withStatus(status)
				search(mGathererRequestBuilder.build())
			}
		})
	}

	@OnCheckedChanged(R.id.multicolor)
	internal fun onSwitchChecked(checked: Boolean) {
		mGathererRequestBuilder.clearMulticolor()
		mGathererRequestBuilder.withMulticolor(checked)
		search(mGathererRequestBuilder.build())
	}

	@OnTextChanged(R.id.page, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
	internal fun onPageInput(text: CharSequence) {
		mGathererRequestBuilder.clearPage()
		if (text.isNotEmpty()) {
			mGathererRequestBuilder.withPage(text.toString().toInt())
		}
		search(mGathererRequestBuilder.build())
	}

	@OnClick(R.id.clear)
	internal fun onClearClicked() {
		mGathererRequestBuilder.clear()
		mTextViewType.text = null
		mTextInputEditTextSubtype.text = null
		mTextViewSupertype.text = null
		mTextInputEditTextName.text = null
		mTextInputEditTextOracle.text = null
		mTextViewRarity.text = null
		mTextInputEditTextSet.text = null
		if (mSwitchMultiColor.isChecked) mSwitchMultiColor.toggle()
		mTextViewColor.text = null
		mTextInputEditTextFormat.text = null
		mTextViewStatus.text = null
		mTextInputEditTextPage.text = null
		mTextViewError.text = null
		mCardAdapter!!.clearCards()
	}
}
