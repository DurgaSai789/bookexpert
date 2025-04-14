package com.example.bookexpert.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun APIListComposable() {

    val myViewModel: ApiListViewModel = viewModel()
    myViewModel.getList()
    val listCalling = myViewModel.transactions.collectAsState()
    val listLoading = myViewModel.loading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "API List",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),

            )
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth().height(2.dp))

        if (listLoading.value){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listCalling.value) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        InfoRow("ID", item.id)
                        InfoRow("Name", item.name)
                        InfoRow("Color", item.data?.smallColor)
                        InfoRow("Capital Color", item.data?.capitalColor)
                        InfoRow("Capacity", item.data?.capacity)
                        InfoRow("CapacityGB", item.data?.capacityGB.toString())
                        InfoRow("Price", item.data?.price.toString())
                        InfoRow("Generation", item.data?.generation)
                        InfoRow("Year", item.data?.year.toString())
                        InfoRow("Cpu Model", item.data?.cpuModel)
                        InfoRow("Hard Disk Size", item.data?.hardDiskSize)
                        InfoRow("Strap Colour", item.data?.strapColour)
                        InfoRow("Case Size", item.data?.caseSize)
                        InfoRow("Description", item.data?.description)
                        InfoRow("Capital Capacity", item.data?.capitalCapacity)
                        InfoRow("Screen Size", item.data?.screenSize.toString())
                        InfoRow("Capital Generation", item.data?.capitalGeneration)
                        InfoRow("Capital Price", item.data?.capitalPrice)
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(tag: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Text(
            text = "$tag:",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value.takeUnless { it.isNullOrBlank() } ?: "--",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(2f)
        )
    }
}

