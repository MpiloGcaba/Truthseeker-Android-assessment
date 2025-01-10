package com.glucode.about_you.utils

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.fragment.app.Fragment


fun Fragment.registerImagePicker(onImageSelected: (uri: Uri) -> Unit): ActivityResultLauncher<String> {
    val pickImageLauncher = registerForActivityResult(GetContent()) { uri: Uri? ->
        uri?.let { onImageSelected(it) }
    }
    return pickImageLauncher
}

fun launcherForImagePicker(launcher: ActivityResultLauncher<String>){
    launcher.launch("image/*")
}