package com.ozman.myappinitial2.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozman.myappinitial2.R
import com.ozman.myappinitial2.data.response.Item

/**
 * Composable function that displays a list of items.
 * Fetches the items from the ViewModel and displays them in a LazyColumn.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(viewModel: ItemsViewModel = hiltViewModel()) {
    // Collect the list of items from the ViewModel
    val items by viewModel.items.collectAsState()
    // Collect the loading state from the ViewModel
    val isLoading by viewModel.isLoading.collectAsState()
    // Collect the error message from the ViewModel
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            // Define the top app bar with a title
            TopAppBar(
                title = { Text("Item List") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) {
        // Create a Box to center the content within the screen
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                // Show a circular progress indicator if the data is loading
                isLoading -> CircularProgressIndicator()
                // Show an error message if there was an error
                errorMessage != null -> Text(
                    text = stringResource(id = R.string.error_message),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
                // Show a message if there are no items
                items.isEmpty() -> Text(
                    text = stringResource(id = R.string.no_items),
                    style = MaterialTheme.typography.bodyLarge
                )
                // Show the list of items
                else -> ItemList(items)
            }
        }
    }
}

/**
 * Composable function that displays a list of items grouped by listId.
 * @param items List of items to be displayed.
 */
@Composable
fun ItemList(items: List<Item>) {
    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
        items.groupBy { it.listId }.forEach { (listId, groupedItems) ->
            item {
                // Display the list ID as a header
                Text(
                    text = "List ID: $listId",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
            // Display each item in the list
            items(groupedItems) { item ->
                ItemRow(item)
            }
        }
    }
}