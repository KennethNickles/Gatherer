package com.github.kennethnickles.gatherer.demo

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.kennethnickles.gatherer.DeckBrewClient
import com.github.kennethnickles.gatherer.card.Card
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

    @BindView(R.id.view_progress_loading)
    lateinit var mProgressLoadingView: ContentLoadingProgressBar;
    @BindView(R.id.scroll_view_error)
    lateinit var mScrollViewError: ScrollView
    @BindView(R.id.text_view_error)
    lateinit var mTextViewError: TextView
    @BindView(R.id.recycler_view_cards)
    lateinit var mRecyclerView: RecyclerView

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

        view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val searchView: View = ButterKnife.findById(activity, R.id.view_search)
                imm.hideSoftInputFromWindow(searchView.windowToken, 0);
                return false
            }
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

        val request: GathererRequest = GathererRequest.builder().build();
        search(request)
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
                mTextViewError.text = Log.getStackTraceString(e)
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
}
