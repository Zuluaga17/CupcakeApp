package com.example.cupcakeapp.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcakeapp.R
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit = {},
    onSendButtonClicked: (String, String) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val numberOfCupcakes = context.resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity
    )

    val orderSummary = """
        Quantity: $numberOfCupcakes
        Flavor: ${orderUiState.flavor}
        Pickup date: ${orderUiState.date}
        Total: ${orderUiState.price}
    """.trimIndent()

    val newOrder = context.getString(R.string.new_cupcake_order)

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(R.string.order_summary),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.thickness_divider))
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = stringResource(R.string.quantity),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = numberOfCupcakes,
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider(
                    thickness = dimensionResource(R.dimen.thickness_divider),
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
                )

                Text(
                    text = stringResource(R.string.flavor),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = orderUiState.flavor,
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider(
                    thickness = dimensionResource(R.dimen.thickness_divider),
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
                )

                Text(
                    text = stringResource(R.string.pickup_date),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = orderUiState.date,
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider(
                    thickness = dimensionResource(R.dimen.thickness_divider),
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
                )

                Text(
                    text = stringResource(R.string.total),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = orderUiState.price,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    onSendButtonClicked(newOrder, orderSummary)
                }
            ) {
                Text(stringResource(R.string.send))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSummaryScreenPreview() {
    CupcakeAppTheme {
        OrderSummaryScreen(
            orderUiState = OrderUiState(
                quantity = 6,
                flavor = "Vanilla",
                date = "Mon Dec 25",
                price = "$12.00"
            )
        )
    }
}

