package com.example.jetpackcompose.data.repository

import android.content.Context
import androidx.core.content.edit

class LocalAuthManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("news_app_auth_prefs", Context.MODE_PRIVATE)

    fun rememberAuth(id: String){
        sharedPreferences.edit(commit = true) {
            putString("id", id)
        }
    }

    fun getCurrentUserId(): String? {
        return sharedPreferences.getString("id", null)
    }

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getString("id", null) != null
    }

    fun signOut(){
        sharedPreferences.edit(commit = true) {remove("id")}
    }
}