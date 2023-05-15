package com.andi.gmovie.ui.screen.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.andi.gmovie.utils.AppBarState

@Composable
fun AboutScreen(
    modifier: Modifier=Modifier,
   onComposing:(AppBarState)->Unit
){

    LaunchedEffect(key1 = true ){
        onComposing(AppBarState("About"))
    }
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()

    ){
        Column(
            modifier = modifier
        ) {
            AsyncImage(
                model = "https://andisety.github.io/portfolio/img/hero.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(500.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = modifier.height(10.dp))

            Text(
                text = "ANDI SETYO PURNOMO",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold),
                modifier = modifier.padding(horizontal = 10.dp)
            )

            Text(
                text = "Email : andisetyo5252@gmail.com",
                modifier = Modifier.padding(vertical = 8.dp).padding(horizontal = 10.dp)
            )

        }
    }

}


