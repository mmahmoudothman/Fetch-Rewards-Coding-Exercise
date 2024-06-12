package com.ozman.myappinitial2.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozman.myappinitial2.data.response.Item
import com.ozman.myappinitial2.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the state of the item list screen.
 * Fetches items from the repository and exposes them as StateFlow.
 * @param repository Repository instance used to fetch items.
 */
@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    // MutableStateFlow for holding the list of items
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    // MutableStateFlow for holding the loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // MutableStateFlow for holding the error message
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        // Fetch items when the ViewModel is initialized
        fetchItems()
    }

    /**
     * Fetches items from the repository.
     * Updates the loading state and items list accordingly.
     */
    private fun fetchItems() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getItems().collect { result ->
                _items.value = result
                _isLoading.value = false
            }
        }
    }
}