package com.andi.gmovie.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.andi.gmovie.ui.theme.GMovieTheme

@Composable
fun DetailMovie(
    modifier: Modifier = Modifier,
    title:String,
    url:String,
    genre:String,
    date:String,
    rate:Double,
    desc:String,
    check:Boolean,
    addFavorite:()->Unit
){
    val checkedState = rememberSaveable{ mutableStateOf(check) }
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()

    ){
        Column(
            modifier = modifier
        ) {
            AsyncImage(
                model = "http://image.tmdb.org/t/p/w500/"+url,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = modifier.height(10.dp))

            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold),
                )
                IconToggleButton(
                    checked = checkedState.value,
                    onCheckedChange = {
                        addFavorite()
                        checkedState.value = !checkedState.value
                    },
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 5.dp)
                    ,) {
                    Icon(
                        imageVector = if (checkedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Icon",
                    )
                }
            }

            Text(
                text = "Genre : $genre",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
            )
            Text(text = "Relase Date : "+date ,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            )
            RatingBar(
                rating =rate,
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .height(20.dp)
                     )

            Text(
                text = "Description : "+desc,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailMoviePrev(){
    GMovieTheme {
//        DetailMovie(
//            title = "Avatar",
//            url = "", genre ="Action",
//            date = "2022-12-22",
//            rate =3.5,
//            desc = "Hahahah ahbadhjah ajdjabdjhb",
//            addFavorite = {},
//            checked =
//            )
    }
}