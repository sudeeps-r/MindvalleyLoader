package com.myimage.caching.view.tiles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.myimage.caching.R
import com.myimage.caching._core.di.component.Injectable
import com.myimage.caching._core.di.module.ViewModelFactory
import com.myimage.caching._core.domain.data.tile_service.Tile
import com.myimage.caching.view.tiles.adapter.TileAdapter
import com.myimage.caching.view.util.BaseFragment
import com.myimage.caching.view.util.EndlessScrollListener
import kotlinx.android.synthetic.main.listview.*
import kotlinx.android.synthetic.main.listview.view.*
import learning.sudeep.imageloader.MindValley
import javax.inject.Inject

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class TileFragment : BaseFragment(), Injectable, SwipeRefreshLayout.OnRefreshListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tileViewModel: TileViewModel
    private val tileAdapter = TileAdapter()
    private lateinit var endlessScrollListener: EndlessScrollListener

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.listview, container, false)

    private fun init() {

        listView.adapter = tileAdapter
        endlessScrollListener = object : EndlessScrollListener(listView.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Log.e("Load more", "Load more items")
                if (totalItemsCount < 100) {
                    tileAdapter.showLoader(true)
                    fetchData(false)
                } else {
                    this.clearLoading()
                }

            }

        }
        listView.addOnScrollListener(endlessScrollListener)
        tileViewModel = ViewModelProviders.of(this, viewModelFactory).get(TileViewModel::class.java)

        tileViewModel.getTileList().observe(this, Observer { it -> refreshData(it) })
        tileViewModel.getErrorResponse().observe(this, Observer { it -> showError(it.message);swiperefresh.isRefreshing = false;refreshList.isEnabled = true;endlessScrollListener.clearLoading();tileAdapter.showLoader(false) })
        swiperefresh.setOnRefreshListener(this)

        refreshList.setOnClickListener {
            refreshList.isEnabled = false
            swiperefresh.isRefreshing = true
            onRefresh()
        }

        fetchData(true)
    }

    override fun onRefresh() {
        fetchData(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!tileViewModel.getDisposable().isDisposed) {
            tileViewModel.getDisposable().dispose()
        }
    }

    fun refreshData(list: List<Tile>) {
        refreshList.isEnabled = true
        tileAdapter.addItems(list, true)
        swiperefresh.isRefreshing = false
        endlessScrollListener.clearLoading()
    }

    fun fetchData(isLoading: Boolean) {
        tileViewModel.fetchTileList()
        swiperefresh.isRefreshing = isLoading
        if (isLoading) {
            tileAdapter.addItems(listOf(), false)
        }

    }
}