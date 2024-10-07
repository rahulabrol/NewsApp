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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.rahul.newsapp.local.entity.LocalArticle

/**
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun ArticleItem(article: () -> LocalArticle, onArticleItemClick: (Uri) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
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
                .clip(RoundedCornerShape(12.dp))
                .constrainAs(articleImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                    height = Dimension.value(200.dp)
                },
            model = article().imageUrl.toUri(),
            contentDescription = article().title,
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(titleText) {
                    start.linkTo(parent.start)
                    top.linkTo(articleImage.bottom, 4.dp)
                    end.linkTo(parent.end)
                },
            text = article().title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        if (article().description.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(descriptionText) {
                        start.linkTo(titleText.start)
                        top.linkTo(titleText.bottom)
                        end.linkTo(parent.end)
                    },
                text = article().description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                letterSpacing = TextUnit(.2F, TextUnitType.Sp),
                lineHeight = TextUnit(16F, TextUnitType.Sp)
            )
        }
        val descAnchor =
            if (article().description.isNotEmpty()) descriptionText.bottom else titleText.bottom
        if (article().localSource.name.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(sourceText) {
                        start.linkTo(parent.start)
                        top.linkTo(descAnchor)
                        end.linkTo(parent.end)
                    },
                text = article().localSource.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
