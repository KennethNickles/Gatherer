package com.github.kennethnickles.gatherer.demo

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
import com.github.kennethnickles.gatherer.Gatherer
import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Color
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.util.Lists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), CardSelectionListener {

    companion object {
        val TAG: String = MainActivityFragment::class.java.simpleName
        val CARD_LIST_KEY: String = "card_list_key"
    }

    @BindView(R.id.view_progress_loading)
    lateinit var mProgressLoadingView: ContentLoadingProgressBar;
    @BindView(R.id.text_view_error)
    lateinit var mTextViewError: TextView
    @BindView(R.id.recycler_view_cards)
    lateinit var mRecyclerView: RecyclerView

    private var mCards: ArrayList<Card>? = null
    private var mCardAdapter: CardAdapter? = null

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

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = layoutManager

        mCards = Lists.newArrayList();
        mCardAdapter = CardAdapter(context, mCards!!, this)
        mRecyclerView.adapter = mCardAdapter;

        mProgressLoadingView.show();

        val request: GathererRequest = GathererRequest.builder().withColor(Color.blue).build();
        Gatherer.cards(request, object : Callback<List<Card>> {

            override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {
                mCards!!.clear();
                mCards!!.addAll(response.body());
                mCardAdapter!!.addCards(response.body())
                mProgressLoadingView.hide()
            }

            override fun onFailure(call: Call<List<Card>>, t: Throwable) {
                mProgressLoadingView.hide()
                mTextViewError.visibility = View.VISIBLE
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putParcelableArrayList(CARD_LIST_KEY, mCards!!)
        super.onSaveInstanceState(outState)
    }

    override fun onCardSelected(card: Card) {
        Snackbar.make(view!!, card.name, Snackbar.LENGTH_LONG)
                .setAction(R.string.details, { Log.d(TAG, card.name) })
                .show();
    }
}
