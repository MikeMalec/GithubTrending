package com.example.githubtrending.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framework.utils.Constants
import com.example.githubtrending.R

@Composable
fun GenericToolbar(
    title: String, showFilter: Boolean = false, showFilterCallback: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                .padding(8.dp), color = Color.White
        )
        if (showFilter) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                contentDescription = "bg",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        showFilterCallback?.invoke()
                    }
                    .testTag(Constants.TestTags.SHOW_FILTERS)
            )
        }
    }
}

@Composable
fun NoContent() {
    Column(modifier = Modifier.testTag(Constants.TestTags.NO_CONTENT)) {
        Image(
            modifier = Modifier.size(100.dp, 100.dp), painter = painterResource(
                id = R.drawable
                    .ic_gh
            ),
            contentDescription = ""
        )
        Text(text = "Nothing found", fontWeight = FontWeight.Bold)
    }
}
