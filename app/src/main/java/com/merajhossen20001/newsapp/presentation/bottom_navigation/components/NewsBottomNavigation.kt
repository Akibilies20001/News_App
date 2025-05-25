package com.merajhossen20001.newsapp.presentation.bottom_navigation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
){
    NavigationBar(
        Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed{ index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = {onItemClick(index)},
                icon = {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){

                        Icon(painter = painterResource(item.icon) ,
                            contentDescription = null,
                            modifier = Modifier.size(Dimensions.DefaultIconSize)
                        )

                        Spacer(modifier = Modifier.height(Dimensions.SmallPadding))
                        Text(text =  item.name, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(R.color.body),
                    unselectedTextColor = colorResource(R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                    )
            )

        }
    }

}


data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val name : String
)

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    NewsAppTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(icon = R.drawable.baseline_home_24, name = "Home"),
                BottomNavigationItem(icon = R.drawable.ic_search, name = "Search"),
                BottomNavigationItem(icon = R.drawable.baseline_bookmark_border_24, name = "Bookmark")
            ),
            selected = 0,
            onItemClick = {}
        )
    }
}