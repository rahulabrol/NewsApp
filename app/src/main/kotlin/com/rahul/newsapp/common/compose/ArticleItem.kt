package com.rahul.newsapp.common.compose

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.rahul.newsapp.local.entity.Article

/**
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun ArticleItem(article: () -> Article, onArticleItemClick: (Uri) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .padding(all = 12.dp)
            .clickable {
                onArticleItemClick(
                    article().url
                        .toUri()
                )
            }
    ) {
        val (articleImage, titleText, descriptionText, sourceText) = createRefs()

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(articleImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                    height = Dimension.value(300.dp)
                }
                .background(Color.White),
            model = article().imageUrl.toUri(),
            contentDescription = article().title)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .constrainAs(titleText) {
                    start.linkTo(parent.start)
                    top.linkTo(articleImage.bottom, 4.dp)
                    end.linkTo(parent.end)
                },
            text = article().title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .constrainAs(descriptionText) {
                    start.linkTo(titleText.start)
                    top.linkTo(titleText.bottom, 4.dp)
                    end.linkTo(parent.end)
                },
            text = article().description,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .constrainAs(sourceText) {
                    start.linkTo(parent.start)
                    top.linkTo(descriptionText.bottom, 4.dp)
                    end.linkTo(parent.end)
                },
            text = article().source.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}