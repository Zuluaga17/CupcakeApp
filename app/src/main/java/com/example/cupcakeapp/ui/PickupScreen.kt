package com.example.cupcakeapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcakeapp.R
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme

@Composable
fun SelectPickupScreen(
    subtotal: String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = "Choose Pickup Date",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Lista de fechas con RadioButtons
        options.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.thickness_divider))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                }
            }
        }

        // Mostrar subtotal
        Text(
            text = "Subtotal: $subtotal",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectPickupScreenPreview() {
    CupcakeAppTheme {
        SelectPickupScreen(
            subtotal = "$12.00",
            options = listOf("Mon Dec 25", "Tue Dec 26", "Wed Dec 27", "Thu Dec 28")
        )
    }
}

