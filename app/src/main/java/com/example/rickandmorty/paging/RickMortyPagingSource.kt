package com.example.rickandmorty.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.models.Characters
import com.example.rickandmorty.data.network.CharacterApiClient

class RickMortyPagingSource(
    private val characterApiClient: CharacterApiClient,
) : PagingSource<Int, Characters>() {

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        return try {
            val currentPage = params.key ?: 1
            val response = characterApiClient.getAllCh(currentPage)
            val responseData = mutableListOf<Characters>()
            val data = (response.body()?.results?: emptyList()) as List<Characters>
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else 1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
