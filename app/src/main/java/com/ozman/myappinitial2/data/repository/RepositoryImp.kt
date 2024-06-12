package com.ozman.myappinitial2.data.repository

import com.ozman.myappinitial2.data.remote.ApiService
import com.ozman.myappinitial2.data.response.Item
import com.ozman.myappinitial2.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(val apiService: ApiService) : Repository {

    override fun getItems(): Flow<List<Item>> = flow {
        val response = apiService.getItems()
        val filteredItems = response.filter { !it.name.isNullOrBlank() }
        val groupedItems = filteredItems.groupBy { it.listId }
        val sortedItems = groupedItems.flatMap { it.value.sortedBy { item -> item.name } }
        emit(sortedItems)
    }
}