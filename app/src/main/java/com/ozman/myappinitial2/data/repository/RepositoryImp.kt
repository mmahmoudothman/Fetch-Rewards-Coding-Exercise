package com.ozman.myappinitial2.data.repository

import com.ozman.myappinitial2.data.remote.ApiService
import com.ozman.myappinitial2.data.response.Item
import com.ozman.myappinitial2.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository implementation that fetches and processes data from the API.
 * @param apiService Instance of ApiService used to make network calls.
 */
class RepositoryImp @Inject constructor(private val apiService: ApiService) : Repository {

    /**
     * Fetches items from the API, processes them, and emits a sorted list of items.
     * @return Flow emitting the list of processed items.
     */
    override fun getItems(): Flow<List<Item>> = flow {
        // Fetch items from API
        val response = apiService.getItems()
        // Filter out items with blank or null names
        val filteredItems = response.filter { !it.name.isNullOrBlank() }
        // Group items by listId
        val groupedItems = filteredItems.groupBy { it.listId }
        // Sort items within each group by name
        val sortedItems = groupedItems.flatMap { it.value.sortedBy { item -> item.name } }
        // Emit the sorted list of items
        emit(sortedItems)
    }.catch { e ->
        // Handle errors by emitting an empty list
        emit(emptyList())
    }
}
