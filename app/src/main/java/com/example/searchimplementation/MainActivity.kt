package com.example.searchimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat

class MainActivity : AppCompatActivity() {
    private var searchItem: MenuItem? = null
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        searchItem = menu?.findItem(R.id.menu_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        return super.onCreateOptionsMenu(menu)

    }
}