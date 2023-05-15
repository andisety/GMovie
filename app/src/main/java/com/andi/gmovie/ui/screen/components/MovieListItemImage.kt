package com.andi.gmovie.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun MovieListItemImage(
    title:String,
    photoUrl:String,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier.clickable{}){
        AsyncImage(
            model = photoUrl,
            contentDescription =title,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .width(150.dp)
                .height(200.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))

        )
    }

}