package com.github.kennethnickles.gatherer.demo

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.github.kennethnickles.gatherer.demo.dagger.ActivityComponent
import com.github.kennethnickles.gatherer.demo.dagger.ActivityModule
import com.github.kennethnickles.gatherer.demo.dagger.ApplicationComponent

class GathererActivity : AppCompatActivity() {

    companion object {
        @JvmField
        val TAG: String = GathererActivity::class.java.simpleName
    }

    private var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        injectSelf()

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        })
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

        handleIntent(intent!!);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchAction: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView = SearchView(this);
        searchView.queryHint = getString(R.string.search_hint)
        searchAction.actionView = searchView
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // If the nav drawer is open, hide action items related to the content view
        menu!!.findItem(R.id.action_search).isVisible = true
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search -> Log.d(TAG, "Action Search")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH.equals(intent.action)) {
            val query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "Search Query for: " + query)
            //use the query to search your data somehow
        }
    }
}
