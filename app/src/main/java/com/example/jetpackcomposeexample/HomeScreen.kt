package com.example.jetpackcomposeexample

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(openViewAll: (FlowersData) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomTopAppBar()
            },
            content = {
                Surface(modifier = Modifier.fillMaxSize(), color = colorPrimary) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            SlidingBanner()
                        }
                        item {
                            Row(
                                modifier = Modifier.padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    bottom = 20.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Popular Items",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                                ClickableText(
                                    text = AnnotatedString("View All"),
                                    onClick = {
                                        openViewAll(FlowersData)
                                    },
                                    style = MaterialTheme.typography.subtitle2.copy(color = Color.White)
                                )
                            }
                        }
                        item {
                            PopularFlowersList()
                        }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SlidingBanner() {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = 3,
        state = pagerState,
        itemSpacing = 20.dp,
    ) { page ->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            bitmap = ImageBitmap.imageResource(id = R.drawable.banner),
            contentScale = ContentScale.FillWidth,
            contentDescription = "sliding_banner_image"
        )
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .padding(16.dp),
    )
}

@Composable
private fun PopularFlowersList() {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(FlowersData.list.size) {
            FlowerCard(FlowersData.list[it])
        }
    }
}

@Composable
private fun FlowerCard(flower: Flowers) {
    var noOfItems by rememberSaveable { mutableStateOf(0) }

    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Image(
                modifier = Modifier.size(140.dp),
                bitmap = ImageBitmap.imageResource(id = flower.image),
                contentDescription = "flower_card"
            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = flower.name,
                        style = TextStyle(
                            color = gray,
                            fontSize = 16.sp,
                        )
                    )
                    Text(
                        text = flower.price,
                        style = TextStyle(
                            color = colorPrimary,
                            fontSize = 16.sp,
                        )
                    )
                }

                Text(
                    text = noOfItems.toString(),
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 10.dp
                    ),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = colorPrimary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            noOfItems++
                            flower.cartCount = noOfItems
                        }
                ) {
                    Icon(
                        Icons.Default.Add,
                        tint = Color.White,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = "flower_card_icon",
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTopAppBar() {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Bouqet Bonanza",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 22.sp
                    )
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { }
                ) {
                }
            }
        },
        backgroundColor = colorPrimary,
    )
}