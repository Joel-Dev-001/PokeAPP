package com.jotadev.pokeapp.ui.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.jotadev.pokeapp.ui.presentation.theme.orange

@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
            onSearch(it)
        },
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .background(orange),
        placeholder = {
            Text(
                text = "Buscar Pokémon...",
                color = orange,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = orange
            )
        },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = orange,
            unfocusedIndicatorColor = orange,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            cursorColor = orange
        )
    )
}