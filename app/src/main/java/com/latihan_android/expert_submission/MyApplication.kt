package com.latihan_android.expert_submission

import android.app.Application
import com.latihan_android.core.di.detailDatabaseModule
import com.latihan_android.core.di.favoriteDatabaseModule
import com.latihan_android.core.di.networkModule
import com.latihan_android.core.di.repositoryModule
import com.latihan_android.expert_submission.di.useCaseModule
import com.latihan_android.expert_submission.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                   favoriteDatabaseModule,
                   detailDatabaseModule,
                   networkModule,
                   repositoryModule,
                   useCaseModule,
                   viewModelModule

                )
            )
        }
    }
}