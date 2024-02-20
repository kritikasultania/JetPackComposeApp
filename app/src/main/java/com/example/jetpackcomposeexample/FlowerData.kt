package com.example.jetpackcomposeexample

object FlowersData {
    val list = listOf(
        Flowers(name = "Red Rose",
            price = "INR 300.00",
            image = R.drawable.img_4,
            cartCount = 0
        ),
        Flowers(name = "Peach Rose",
            price = "INR 550.00",
            image = R.drawable.img_1,
            cartCount = 0
        ),
        Flowers(name = "Mix Flowers",
            price = "INR 570.00",
            image = R.drawable.img_2,
            cartCount = 0
        ),
        Flowers(name = "Chinese Pink",
            price = "INR 650.00",
            image = R.drawable.img_3,
            cartCount = 0
        )
    )
}

data class Flowers(
    val name: String,
    val price: String,
    val image: Int,
    var cartCount: Int
)
