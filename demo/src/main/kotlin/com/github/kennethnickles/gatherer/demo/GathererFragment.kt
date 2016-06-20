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
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.kennethnickles.gatherer.DeckBrewClient
import com.github.kennethnickles.gatherer.GathererClient
import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Color
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
class GathererFragment : Fragment(), CardSelectionListener {

    companion object {
        val TAG: String = GathererFragment::class.java.simpleName
        val CARD_LIST_KEY: String = "card_list_key"
    }

    @Inject
    lateinit var mDeckBrewClient: DeckBrewClient
    @Inject
    lateinit var mGathererClient: GathererClient

    @BindView(R.id.view_progress_loading)
    lateinit var mProgressLoadingView: ContentLoadingProgressBar;
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

        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view!!);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        injectSelf()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = layoutManager

        mCards = Lists.newArrayList();
        mCardAdapter = CardAdapter(context, mCards!!, this)
        mRecyclerView.adapter = mCardAdapter

        mProgressLoadingView.show();

        val request: GathererRequest = GathererRequest.builder().withColor(Color.BLUE).build();
        mDeckBrewClient.cards(request).subscribe(object : Observer<List<Card>> {
            override fun onNext(cards: List<Card>?) {
                mCards!!.clear();
                mCards!!.addAll(cards!!);
                mCardAdapter!!.addCards(cards!!)
                mProgressLoadingView.hide()
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                Log.e(TAG, Log.getStackTraceString(e))
                mProgressLoadingView.hide()
                mTextViewError.text = Log.getStackTraceString(e)
                mTextViewError.visibility = View.VISIBLE
            }
        })
    }

    fun injectSelf() {
        getFragmentComponent().injectGathererFragment(this)
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

    fun onSearchClick() {
        Log.d(TAG, "onSearchClick")
    }

    fun onSearchClose(): Boolean {
        Log.d(TAG, "onSearchClose")
        return false
    }

    fun onSearch(query: String?): Boolean {
        Log.d(TAG, "onQueryTextSubmit")
        return false
    }

    fun onSearchTextChange(newText: String?): Boolean {
        Log.d(TAG, "onQueryTextChange")
        return false
    }
}
