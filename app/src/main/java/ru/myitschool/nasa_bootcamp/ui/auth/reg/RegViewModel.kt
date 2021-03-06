package ru.myitschool.nasa_bootcamp.ui.auth.reg

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import ru.myitschool.nasa_bootcamp.utils.Data

interface RegViewModel {
    suspend fun createUser(context: Context, userName: String, email: String, password: String, imagePath: Uri?): Data<out FirebaseUser>
    fun getViewModelScope(): CoroutineScope
}
