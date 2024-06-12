package com.ozman.myappinitial2.domain.repository

import com.ozman.myappinitial2.data.response.Item
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getItems(): Flow<List<Item>>
}