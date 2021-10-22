package com.example.seprojectsemester5.authentication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDetails(
    val context: Context
) {
    private val Context.dataStore:
            DataStore<Preferences> by preferencesDataStore(name = "user_details")

    companion object{
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val JWT_TOKEN = stringPreferencesKey("jwtToken")
    }

    suspend fun storeEmail(email : String){
        context.dataStore.edit {
            it[EMAIL] = email
        }
    }

    suspend fun storeName(name : String){
        context.dataStore.edit {
            it[NAME] = name
        }
    }

    suspend fun storeJwtToken(jwtToken : String){
        context.dataStore.edit {
            it[JWT_TOKEN] = jwtToken
        }
    }

    val email : Flow<String?>
        get() = context.dataStore.data.map{
            it[EMAIL]
        }

    val name : Flow<String?>
        get() = context.dataStore.data.map{
            it[NAME]
        }

    val jwtToken : Flow<String?>
        get() = context.dataStore.data.map{
            it[JWT_TOKEN]
        }
}