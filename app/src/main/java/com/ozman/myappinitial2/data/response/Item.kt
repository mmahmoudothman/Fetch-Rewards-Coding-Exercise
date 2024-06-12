package com.ozman.myappinitial2.data.response

/**
 * Data model for Item.
 * Represents an item fetched from the API.
 * @param id Unique identifier for the item.
 * @param listId Identifier for the list to which the item belongs.
 * @param name Name of the item, which may be null.
 */
data class Item(
    val id: Int,
    val listId: Int,
    val name: String?
)
