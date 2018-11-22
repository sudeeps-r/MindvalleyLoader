package com.myimage.caching.view.tiles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myimage.caching.R
import com.myimage.caching.core.di.component.Injectable
import com.myimage.caching.core.domain.data.tileService.Tile
import com.myimage.caching.databinding.ListviewBinding
import com.myimage.caching.view.binding.RefreshCallback
import com.myimage.caching.view.binding.RetryCallback
import com.myimage.caching.view.util.BaseFragment
import com.myimage.caching.view.util.EndlessScrollListener
import com.myimage.caching.view.binding.autoCleared
import com.myimage.caching.view.tiles.adapter.TileAdapter
import kotlinx.android.synthetic.main.listview.*
import javax.inject.Inject

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class TileFragment : BaseFragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tileViewModel: TileViewModel
    private val tileAdapter = TileAdapter(R.layout.list_row,R.layout.load_more)
    private lateinit var endlessScrollListener: EndlessScrollListener
    private var databing by autoCleared<ListviewBinding>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View{
        var dataBindingUtil=DataBindingUtil.inflate<ListviewBinding>(inflater,R.layout.listview,container,false)
        databing=dataBindingUtil
       
        return dataBindingUtil.root
    }

    private fun init() {

        databing.listView.adapter = tileAdapter
        endlessScrollListener = object : EndlessScrollListener(listView.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Log.e("Load more", "Load more items")
                if (totalItemsCount < 100) {
                    tileAdapter.toggleLoader(true)
                    fetchData(false)
                } else {
                    this.clearLoading()
                }

            }

        }
        databing.listView.addOnScrollListener(endlessScrollListener)
        tileViewModel = ViewModelProviders.of(this, viewModelFactory).get(TileViewModel::class.java)
        databing.viewModel=tileViewModel
        tileViewModel.getTileList().observe(this, Observer { it -> refreshData(it) })
        tileViewModel.getErrorResponse().observe(this, Observer { it -> showError(it.message);tileViewModel.refreshStatus(false);refreshList.isEnabled = true;endlessScrollListener.clearLoading();tileAdapter.toggleLoader(false) })




        databing.retryCallback=object :RetryCallback{
            override fun retry() {
                databing.refreshList.isEnabled=false
                fetchData(true)
            }

        }
        databing.refreshCallback=object :RefreshCallback{
            override fun refresh() {
                fetchData(true)
            }

        }
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
        tileViewModel.refreshStatus(false)
        endlessScrollListener.clearLoading()
    }

    fun fetchData(isLoading: Boolean) {
        tileViewModel.fetchTileList(isLoading)
        if (isLoading) {
            tileAdapter.addItems(listOf(), false)
        }

    }
}