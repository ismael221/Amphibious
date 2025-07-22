package com.ismael.amphibians.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ismael.amphibians.R
import com.ismael.amphibians.model.Amphibian
import com.ismael.amphibians.ui.theme.AmphibiansTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title ={ Text(text = "Amphibians", style = MaterialTheme.typography.headlineMedium)}
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
           val amphibianViewModel: AmphibianViewModel =
               viewModel(factory = AmphibianViewModel.Factory)

            AmphibianHomeScreen(
                amphibianUiState = amphibianViewModel.amphibianUiState,
                modifier = Modifier.padding(top = 50.dp)
            )

        }
    }
}

@Composable
fun AmphibianHomeScreen(
    amphibianUiState: AmphibianUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxHeight()
            .padding(8.dp)
    ) {
        when(amphibianUiState){
            is AmphibianUiState.Success -> AmphibiansCardList(
                amphibianUiState.amphibians,
                contentPadding = contentPadding,
                modifier = modifier.fillMaxWidth()
            )
            is AmphibianUiState.Loading -> {}
            is AmphibianUiState.Error -> {}
        }
    }
}


@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.amphibian_title,amphibian.name,amphibian.type),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = amphibian.description,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = amphibian.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}


@Composable
fun AmphibiansCardList(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyColumn(
        contentPadding = contentPadding,
        modifier =  modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(items = amphibians, key = { amphibian -> amphibian.name}){
                amphibian -> AmphibianCard(
                    amphibian
                )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun AmphibianScreenPreview(
    modifier: Modifier = Modifier
){
    AmphibiansTheme {
//        AmphibianCard(
//            modifier = modifier
//        )
    }
}