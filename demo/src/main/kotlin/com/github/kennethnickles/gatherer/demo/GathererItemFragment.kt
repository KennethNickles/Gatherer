package com.github.kennethnickles.gatherer.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.kennethnickles.gatherer.DeckBrewClient
import com.github.kennethnickles.gatherer.card.Set
import com.github.kennethnickles.gatherer.demo.dagger.FragmentComponent
import com.github.kennethnickles.gatherer.demo.dagger.FragmentModule
import rx.Observer
import javax.inject.Inject

/**
 * @author kenneth.nickles
 * @since 2016-06-26.
 */
class GathererItemFragment : Fragment() {

    enum class Item {
        supertypes,
        types,
        subtypes,
        colors,
        sets
    }

    companion object {
        val TAG: String = GathererItemFragment::class.java.simpleName
        val KEY: String = "item"

        @JvmStatic
        fun create(item: Item): GathererItemFragment {
            val fragment = GathererItemFragment()
            val args = Bundle()
            args.putSerializable(KEY, item)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var mDeckBrewClient: DeckBrewClient

    @BindView(R.id.scroll_view)
    lateinit var mScrollView: ScrollView
    @BindView(R.id.text_view_label)
    lateinit var mTextViewLabel: TextView
    @BindView(R.id.text_view_value)
    lateinit var mTextViewValue: TextView

    private var mFragmentComponent: FragmentComponent? = null

    private var item: Item? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater!!.inflate(R.layout.fragment_gatherer_item, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view!!);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        injectSelf()

        item = if (savedInstanceState != null) savedInstanceState!!.getSerializable(
                KEY) as Item else arguments.getSerializable(KEY) as Item

        request(item!!)
    }

    fun injectSelf() {
        getFragmentComponent().injectGathererItemFragment(this)
    }

    fun getFragmentComponent(): FragmentComponent {
        if (mFragmentComponent == null) {
            mFragmentComponent = (activity as GathererActivity).getActivityComponent().plus(FragmentModule(this))
        }
        return mFragmentComponent!!
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putSerializable(KEY, item)
        super.onSaveInstanceState(outState)
    }

    private fun request(item: Item) {
        if (item == Item.supertypes) {
            supertypes()
        }
        if (item == Item.types) {
            types()
        }
        if (item == Item.subtypes) {
            subtypes()
        }
        if (item == Item.colors) {
            colors()
        }
        if (item == Item.sets) {
            sets()
        }
    }

    private fun supertypes() {
        mDeckBrewClient.supertypes().subscribe(itemObserver)
    }

    private fun types() {
        mDeckBrewClient.types().subscribe(itemObserver)
    }

    private fun subtypes() {
        mDeckBrewClient.subtypes().subscribe(itemObserver)
    }

    private fun colors() {
        mDeckBrewClient.colors().subscribe(itemObserver)
    }

    private fun sets() {
        mDeckBrewClient.sets().subscribe(setObserver)
    }

    val itemObserver: Observer<List<String>> = object : Observer<List<String>> {
        override fun onNext(items: List<String>?) {
            val builder = StringBuilder()
            for (item in items!!) {
                builder.append(item).append('\n')
            }
            mTextViewLabel.text = item!!.name
            mTextViewValue.text = builder.toString()
            mTextViewLabel.visibility = View.VISIBLE
            mTextViewValue.visibility = View.VISIBLE
            mScrollView.visibility = View.VISIBLE
        }

        override fun onCompleted() {
        }

        override fun onError(e: Throwable?) {
            Log.e(GathererSearchFragment.TAG, Log.getStackTraceString(e))
            mTextViewLabel.text = item!!.name
            mTextViewValue.text = Log.getStackTraceString(e)
            mTextViewLabel.visibility = View.VISIBLE
            mTextViewValue.visibility = View.VISIBLE
            mScrollView.visibility = View.VISIBLE
        }
    }

    val setObserver: Observer<List<Set>> = object : Observer<List<Set>> {
        override fun onNext(sets: List<Set>?) {
            val builder = StringBuilder()
            for (set in sets!!) {
                builder.append(set.name).append('\n')
            }
            mTextViewLabel.text = item!!.name
            mTextViewValue.text = builder.toString()
            mTextViewLabel.visibility = View.VISIBLE
            mTextViewValue.visibility = View.VISIBLE
            mScrollView.visibility = View.VISIBLE
        }

        override fun onCompleted() {
        }

        override fun onError(e: Throwable?) {
            Log.e(GathererSearchFragment.TAG, Log.getStackTraceString(e))
            mTextViewLabel.text = item!!.name
            mTextViewValue.text = Log.getStackTraceString(e)
            mTextViewLabel.visibility = View.VISIBLE
            mTextViewValue.visibility = View.VISIBLE
            mScrollView.visibility = View.VISIBLE
        }
    }
}