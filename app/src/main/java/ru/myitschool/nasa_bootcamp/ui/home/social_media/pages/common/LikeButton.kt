package ru.myitschool.nasa_bootcamp.ui.home.social_media.pages.common

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import ru.myitschool.nasa_bootcamp.R
import ru.myitschool.nasa_bootcamp.data.model.UserModel
import ru.myitschool.nasa_bootcamp.utils.Resource
import ru.myitschool.nasa_bootcamp.utils.Status

@Composable
fun LikeButton(
    list: List<UserModel>,
    currentUser: UserModel?,
    modifier: Modifier = Modifier,
    onClick: () -> LiveData<Resource<Nothing>>
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            if (currentUser == null) {
                Toast.makeText(
                    context,
                    "You need to log in",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val liveData = onClick()
                liveData.observe(lifecycleOwner) {
                    if (it.status == Status.ERROR)
                        Toast.makeText(
                            context,
                            "Failed to leave a like",
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }
        }, modifier = modifier) {
            Icon(
                painter = painterResource(R.drawable.ic_heart),
                tint = if (currentUser != null && list.map { it.name }.contains(currentUser.name))
                    Color.Red
                else LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                contentDescription = "like"
            )
        }
        Text(text = list.size.toString())
    }
}