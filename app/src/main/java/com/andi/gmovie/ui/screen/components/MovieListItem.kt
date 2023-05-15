package com.andi.gmovie.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun MovieListItem(
    title:String,
    desc:String,
    photoUrl:String,
    modifier: Modifier = Modifier
){
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(vertical = 5.dp, horizontal = 5.dp),
        ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.clickable {  }

        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription =title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
            )
            Column(modifier = modifier.padding(horizontal = 5.dp)){
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = desc,
                    overflow = TextOverflow.Ellipsis,
                    maxLines=2,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}