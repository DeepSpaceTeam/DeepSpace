package ru.myitschool.nasa_bootcamp.ui.view_user_posts

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import ru.myitschool.nasa_bootcamp.data.model.Post
import ru.myitschool.nasa_bootcamp.data.model.PostItem
import ru.myitschool.nasa_bootcamp.data.model.PostView
import ru.myitschool.nasa_bootcamp.utils.Data

interface ViewAllPostViewModel {
    suspend fun getAllPosts(): LiveData<Data<out ArrayList<Post>>>
    suspend fun loadImage(postId: String, imageId: String): LiveData<Data<out Bitmap>>
    suspend fun getAdditionalData(postId: String): LiveData<Data<out ArrayList<PostView>>>
    fun getViewModelScope(): CoroutineScope
}