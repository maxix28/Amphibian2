package com.example.amphibian2

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.amphibian2.model.AmpiUiState
import com.example.amphibian2.model.AmpiViewModel
import com.example.amphibian2.network.AmphibiansItem
import com.example.amphibian2.ui.theme.Amphibian2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Amphibian2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Max")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val amphiViewModel : AmpiViewModel= viewModel(factory = AmpiViewModel.Factory)
    when(amphiViewModel.ampiUiState){
        AmpiUiState.Error ->{
            Column (modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                Text(
                    text = "Error",
                    modifier = modifier
                )



                IconButton(onClick = {amphiViewModel.tryAgain() }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                }
            }
     }
        AmpiUiState.Loading -> {
            Box(modifier= modifier
                .height(50.dp)
                .fillMaxSize(), contentAlignment = Alignment.Center){
//                CircularProgressIndicator(modifier= modifier
//                    .fillMaxSize()
//                    .size(20.dp))
                CircularProgressIndicator()
            }


        }
        is AmpiUiState.Success ->{
            ShowAmphibians((amphiViewModel.ampiUiState as AmpiUiState.Success).a)

        }
    }

}

@Composable
fun ShowAmphibians( amphibiansItems: List<AmphibiansItem> ,  modifier: Modifier=Modifier){
    LazyColumn(modifier = modifier.fillMaxSize()){
        items(amphibiansItems){
            ShowOneAmphibian(it)
        }
    }
}
@Composable
fun ShowOneAmphibian(amphibiansItem: AmphibiansItem   ,modifier: Modifier=Modifier){
Card(modifier = modifier
    .padding(10.dp)
    .fillMaxWidth(), elevation = CardDefaults.cardElevation(10.dp)){
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(text = amphibiansItem.name, style =  MaterialTheme.typography.titleLarge, modifier = modifier
            .align(
                Alignment.CenterHorizontally
            )
            .padding(bottom = 5.dp))
        AsyncImage(model = amphibiansItem.img_src,modifier = modifier.fillMaxWidth(), contentDescription =null, contentScale = ContentScale.Crop, alignment = Alignment.Center )
        Spacer (modifier = modifier.height(10.dp))
        Text(text = amphibiansItem.description, style =  MaterialTheme.typography.titleMedium)

    }

}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Amphibian2Theme {
        Box(modifier= Modifier.height(60.dp)){
            CircularProgressIndicator(modifier= Modifier
                .fillMaxSize()
                .size(20.dp))
        }
    }
}