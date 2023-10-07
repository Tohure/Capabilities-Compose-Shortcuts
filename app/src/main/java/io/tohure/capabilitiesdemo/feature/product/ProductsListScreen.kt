package io.tohure.capabilitiesdemo.feature.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import io.tohure.capabilitiesdemo.model.Product
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductsListScreen() {

    val viewModel = getViewModel<ProductViewModel>()
    viewModel.getProducts()

    val products = viewModel.productList.observeAsState().value ?: emptyList()

    LazyColumn {
        products.forEach { 
            item { 
                ProductItem(
                    it,
                    modifier = Modifier.padding(8.dp))
            }
        }
    }

}

@Composable
fun ProductItem(product: Product, modifier : Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = modifier
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.small),
                model = product.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            ProductInfo(product.title, product.price)
        }
    }
}

@Composable
fun ProductInfo(title: String, price: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = price,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ProductsListScreen()
    }
}