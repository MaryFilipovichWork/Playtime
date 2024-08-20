package com.test.task.playtime.components

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.test.task.playtime.SecondActivity

@Composable
fun StartActivityButton(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Button(
        onClick = {
            Intent(context, SecondActivity::class.java).also {
                startActivity(context, it, null)
            }
        },
        modifier = modifier
    ) {
        Text(text = "Open the second Activity")
    }
}