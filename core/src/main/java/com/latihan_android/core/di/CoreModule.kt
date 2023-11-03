package com.latihan_android.core.di

import androidx.room.Room
import com.latihan_android.core.data.local.LocalDataSource
import com.latihan_android.core.data.local.room.DetailDatabase
import com.latihan_android.core.data.local.room.FavoriteDatabase
import com.latihan_android.core.data.remote.RemoteDataSource
import com.latihan_android.core.data.remote.network.ApiService
import com.latihan_android.core.domain.repository.IUserRepository
import com.latihan_android.core.utils.AppExecutors
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val favoriteDatabaseModule = module {
    factory { get<FavoriteDatabase>().favoriteDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteDatabase::class.java, "Favorite.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val detailDatabaseModule = module {
    factory { get<DetailDatabase>().detailDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DetailDatabase::class.java, "Detail.db"
        ).fallbackToDestructiveMigration().build()
    }
}


val authInterceptor = Interceptor { chain ->
    val req = chain.request()
    val requestHeaders = req.newBuilder()
        .addHeader("Authorization", "Token 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")
        .build()
    chain.proceed(requestHeaders)
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IUserRepository> { com.latihan_android.core.data.UserRepository(get(), get(), get()) }
}