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
import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.demo.dagger.ActivityComponent
import com.github.kennethnickles.gatherer.demo.dagger.ActivityModule
import com.github.kennethnickles.gatherer.demo.dagger.ApplicationComponent

class GathererActivity : AppCompatActivity(), CardSelectionListener {

    companion object {
        @JvmField
        val TAG: String = GathererActivity::class.java.simpleName
    }

    private var activityComponent: ActivityComponent? = null

    private var searchAction: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        injectSelf()

        handleIntent(intent)
    }

    open fun injectSelf() {
        getActivityComponent().injectGathererActivity(this)
    }

    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = getApplicationComponent().plus(ActivityModule(this))
        }
        return activityComponent!!
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (application as GathererApp).applicationComponent
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        handleIntent(intent!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        searchAction = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = layoutInflater.inflate(R.layout.view_search, null, false) as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchAction!!.actionView = searchView
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        searchView.setOnSearchClickListener {
            onSearchClick()
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

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (searchAction!!.isActionViewExpanded) {
            menu!!.findItem(R.id.action_close).isVisible = true
            menu!!.findItem(R.id.action_search).isVisible = false
        } else {
            menu!!.findItem(R.id.action_close).isVisible = false
            menu!!.findItem(R.id.action_search).isVisible = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search -> Log.d(TAG, "Action Search")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCardSelected(card: Card) {
        Log.d(TAG, "onCardSelected: " + card.name)
        searchAction!!.collapseActionView();
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH.equals(intent.action)) {
            val query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "Search Query for: " + query)
            //use the query to search your data somehow
        }
    }

    private fun onSearchClick() {
        Log.d(TAG, "onSearchClick")
    }

    private fun onSearchClose(): Boolean {
        Log.d(TAG, "onSearchClose")
        return false
    }

    private fun onSearch(query: String?): Boolean {
        Log.d(TAG, "onSearch: " + query)
        return false
    }

    private fun onSearchTextChange(newText: String?): Boolean {
        Log.d(TAG, "onSearch: " + newText)
        return false
    }
}
