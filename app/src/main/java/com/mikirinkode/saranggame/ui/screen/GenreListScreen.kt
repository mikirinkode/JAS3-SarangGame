package com.mikirinkode.saranggame.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mikirinkode.saranggame.R
import com.mikirinkode.saranggame.data.response.Genre
import com.mikirinkode.saranggame.ui.components.LoadingIndicator
import com.mikirinkode.saranggame.ui.theme.SarangGameTheme
import com.mikirinkode.saranggame.utils.UiState
import com.mikirinkode.saranggame.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreListScreen(
    viewModel: GameViewModel,
    onGenreClicked: (genreId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = { Text(text = "Genre") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            viewModel.genreListState.collectAsState(UiState.Loading).value.let { state ->
                when (state) {
                    is UiState.Loading -> {
                        viewModel.getGenreList()
                        LoadingIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is UiState.Error -> {} // TODO
                    is UiState.Success -> {
                        val list = state.data
                        GenreList(list = list, onGenreClicked = onGenreClicked)
                    }
                }
            }
        }
    }
}

@Composable
fun GenreList(
    list: List<Genre>,
    onGenreClicked: (genreId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier,
    ) {
        items(list) { genre ->
            GenreCompactCard(
                imageUrl = genre.imageBackground ?: "",
                name = genre.name ?: "",
                totalGames = genre.gamesCount ?: 0,
                onGenreClicked = {
                    val genreId = genre.id ?: 1
                    onGenreClicked(genreId)
                }
            )
        }
    }
}

@Composable
fun GenreCompactCard(
    imageUrl: String,
    name: String,
    totalGames: Int,
    onGenreClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium.copy(topStart = ZeroCornerSize)
            )
    ) {
        Box(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .clickable(onClick = onGenreClicked)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Genre Image",
                    modifier = Modifier
                        .height(50.dp)
                        .width(100.dp)
                        .clip(
                            MaterialTheme.shapes.medium.copy(
                                topEnd = ZeroCornerSize,
                                bottomEnd = CornerSize(8.dp)
                            )
                        ),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_more)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        name,
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "(${Utils.formatNumberToK(totalGames)} Games)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GenreListScreenPreview() {
    SarangGameTheme {
        Surface {
//            GenreListScreen(
//                onGenreClicked = {}
//            )
        }
    }
}