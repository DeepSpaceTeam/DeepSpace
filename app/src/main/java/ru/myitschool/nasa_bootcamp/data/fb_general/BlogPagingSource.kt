package ru.myitschool.nasa_bootcamp.data.fb_general

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.myitschool.nasa_bootcamp.data.model.ContentWithLikesAndComments
import ru.myitschool.nasa_bootcamp.data.model.PostModel
import ru.myitschool.nasa_bootcamp.data.repository.FirebaseRepository
import ru.myitschool.nasa_bootcamp.utils.BLOG_PAGE_SIZE
import ru.myitschool.nasa_bootcamp.utils.Status

class BlogPagingSource(private val firebaseRepository: FirebaseRepository) :
    PagingSource<DataSnapshot, LiveData<ContentWithLikesAndComments<PostModel>>>() {

    override suspend fun load(params: LoadParams<DataSnapshot>):
            LoadResult<DataSnapshot, LiveData<ContentWithLikesAndComments<PostModel>>> {
        return try {
            val currentPage =
                params.key ?: FirebaseDatabase.getInstance().getReference("user_posts")
                    .orderByKey().limitToLast(BLOG_PAGE_SIZE).get()
                    .await()
            val lastVisibleContent = currentPage.children.first().key.toString()
            var nextPage =
                FirebaseDatabase.getInstance().getReference("user_posts").orderByKey()
                    .endBefore(lastVisibleContent).limitToLast(BLOG_PAGE_SIZE).get()
                    .await()
            if (nextPage.value == null)
                nextPage = null
            val postsResource = firebaseRepository.getPostsFromDataSnapshot(currentPage)
            if (postsResource.status == Status.SUCCESS)
                LoadResult.Page(
                    data = postsResource.data!!,
                    prevKey = null,
                    nextKey = nextPage
                )
            else LoadResult.Error(Exception(postsResource.message.toString()))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<DataSnapshot, LiveData<ContentWithLikesAndComments<PostModel>>>): DataSnapshot? {
        return null
    }
}