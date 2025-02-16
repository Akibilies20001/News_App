package com.merajhossen20001.newsapp.presentation.onboarding.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.presentation.Dimensions.MediumPadding1
import com.merajhossen20001.newsapp.presentation.Dimensions.MediumPadding2
import com.merajhossen20001.newsapp.presentation.onboarding.Page
import com.merajhossen20001.newsapp.presentation.onboarding.pages
import com.merajhossen20001.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnBoardingPage(modifier: Modifier = Modifier,
                   page: Page)
{
    Column (modifier = modifier) {
        Image(
            painter = painterResource(page.image),
            contentDescription = page.title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.display_small)
        )
        Spacer(modifier = Modifier.height(MediumPadding1/2))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_medium)
        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPageView()
{
    NewsAppTheme{
        OnBoardingPage(page =pages[0])
    }

}