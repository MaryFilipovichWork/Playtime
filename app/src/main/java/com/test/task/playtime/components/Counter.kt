package com.test.task.playtime.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.test.task.playtime.sdk.ExampleSdk
import com.test.task.playtime.utils.secondsToTime

@Composable
fun Counter(
    modifier: Modifier = Modifier
) {
    val counter by ExampleSdk.getPlaytime().collectAsState()

    Text(
        text = counter.secondsToTime(),
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        modifier = modifier
    )
}