package com.ozman.myappinitial2.domain.repository

import com.ozman.myappinitial2.data.response.Item
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining the contract for fetching items.
 */
interface Repository {
    /**
     * Fetches items from the data source and returns a flow of the list of items.
     * @return Flow emitting the list of items.
     */
    fun getItems(): Flow<List<Item>>
}