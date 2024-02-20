package com.example.jetpackcomposeexample

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ViewAllScreen() {
    DisplayList(FlowersData)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DisplayList(FlowersData: FlowersData) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Scaffold(
                topBar = {
                    CustomTopAppBar()
                },
                content = {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        color = colorPrimary
                    ) {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            backgroundColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {

                            LazyColumn {

                                items(FlowersData.list) { data ->
                                    Image(
                                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp),
                                        bitmap = ImageBitmap.imageResource(id = data.image),
                                        contentDescription = "flower_card"
                                    )
                                    Row(
                                        modifier = Modifier.padding(
                                            top = 20.dp,
                                            bottom = 20.dp,
                                            start = 20.dp
                                        )
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = data.name,
                                                style = TextStyle(
                                                    color = gray,
                                                    fontSize = 16.sp,
                                                )
                                            )
                                            Text(
                                                text = data.price,
                                                style = TextStyle(
                                                    color = colorPrimary,
                                                    fontSize = 16.sp,
                                                )
                                            )

                                            Text(
                                                text = data.cartCount.toString(),
                                                style = TextStyle(
                                                    color = colorPrimary,
                                                    fontSize = 16.sp,
                                                )
                                            )
                                        }
                                    }

                                    Divider(modifier = Modifier.padding(10.dp))
                                }
                            }
                        }
                    }
                })
        }
    }
}