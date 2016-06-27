package com.github.kennethnickles.gatherer.demo

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import butterknife.BindView
import butterknife.ButterKnife
import com.github.kennethnickles.gatherer.DeckBrewClient
import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.demo.dagger.ActivityComponent
import com.github.kennethnickles.gatherer.demo.dagger.ActivityModule
import com.github.kennethnickles.gatherer.demo.dagger.ApplicationComponent
import javax.inject.Inject

class GathererActivity : AppCompatActivity(), CardSelectionListener, SearchListener {

    companion object {
        @JvmField
        val TAG: String = GathererActivity::class.java.simpleName
    }

    @Inject
    lateinit var mDeckBrewClient: DeckBrewClient

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    private var mActivityComponent: ActivityComponent? = null

    private var mSearchAction: MenuItem? = null

    private var mIsCurrentFragmentSearch: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        setSupportActionBar(mToolbar)

        injectSelf()

        if (savedInstanceState == null) {
            launchSearch()
        }

        handleIntent(intent)
    }

    private fun launchSearch() {
        mIsCurrentFragmentSearch = true
        val fragment = supportFragmentManager.findFragmentByTag(GathererSearchFragment.TAG) ?: GathererSearchFragment()
        supportFragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit()
    }

    open fun injectSelf() {
        getActivityComponent().injectGathererActivity(this)
    }

    fun getActivityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = getApplicationComponent().plus(ActivityModule(this))
        }
        return mActivityComponent!!
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (application as GathererApp).applicationComponent
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        handleIntent(intent!!)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH.equals(intent.action)) {
            val query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "Search Query for: " + query)
            //use the query to search your data somehow
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        mSearchAction = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = layoutInflater.inflate(R.layout.view_search, null, false) as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        mSearchAction!!.actionView = searchView
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        searchView.setOnSearchClickListener {
            onSearchOpen()
        }
        searchView.setOnCloseListener {
            onSearchClose()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return onSearch(query)
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return onSearchTextChange(newText)
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search -> launchSearch()
            R.id.action_supertypes -> launchItem(GathererItemFragment.Item.supertypes)
            R.id.action_types -> launchItem(GathererItemFragment.Item.types)
            R.id.action_subtypes -> launchItem(GathererItemFragment.Item.subtypes)
            R.id.action_colors -> launchItem(GathererItemFragment.Item.colors)
            R.id.action_sets -> launchItem(GathererItemFragment.Item.sets)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun launchItem(item: GathererItemFragment.Item) {
        mIsCurrentFragmentSearch = false
        if (mSearchAction!!.isActionViewExpanded) {
            mSearchAction!!.collapseActionView()
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mToolbar.windowToken, 0);
        invalidateOptionsMenu()
        supportFragmentManager.beginTransaction().replace(R.id.content_main, GathererItemFragment.create(item)).commit()
    }

    override fun onBackPressed() {
        if (mSearchAction!!.isActionViewExpanded) {
            mSearchAction!!.collapseActionView()
        }
        super.onBackPressed()
    }

    override fun onCardSelected(card: Card) {
        Log.d(TAG, "onCardSelected: " + card.name)
        mSearchAction!!.collapseActionView();
    }

    override fun onSearchOpen() {
        Log.d(TAG, "onSearchOpen")
        if (!mIsCurrentFragmentSearch) {
            launchSearch()
        }
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is SearchListener) {
                fragment.onSearchOpen()
            }
        }
    }

    override fun onSearchClose(): Boolean {
        Log.d(TAG, "onSearchClose")
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is SearchListener) {
                fragment.onSearchClose()
            }
        }
        return false
    }

    override fun onSearch(query: String?): Boolean {
        Log.d(TAG, "onSearch: " + query)
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is SearchListener) {
                fragment.onSearch(query)
            }
        }
        return false
    }

    override fun onSearchTextChange(newText: String?): Boolean {
        Log.d(TAG, "onSearchTextChange: " + newText)
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is SearchListener) {
                fragment.onSearchTextChange(newText)
            }
        }
        return false
    }
}
