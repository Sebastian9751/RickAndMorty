package com.example.rickandmorty.data.pages

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationScrollListener(private val layoutManager: LinearLayoutManager,
                               private val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {

    private var loading = false
    private val visibleThreshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

        if (!loading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            onLoadMore()
            loading = true
        }
    }

    fun setLoading(loading: Boolean) {
        this.loading = loading
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}
