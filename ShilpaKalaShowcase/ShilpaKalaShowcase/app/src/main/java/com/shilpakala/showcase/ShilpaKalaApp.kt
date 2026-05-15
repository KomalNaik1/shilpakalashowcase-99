package com.shilpakala.showcase

import android.app.Application
import com.shilpakala.showcase.data.seed.DataSeeder
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class ShilpaKalaApp : Application() {
    
    @Inject lateinit var dataSeeder: DataSeeder

    override fun onCreate() {
        super.onCreate()
        // Seed database on first run in a background coroutine
        CoroutineScope(Dispatchers.IO).launch {
            dataSeeder.seed()
        }
    }
}
