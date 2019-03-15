package com.example.myapplication.main

import com.example.myapplication.main.data.remote.MovieRepository
import com.example.myapplication.main.data.remote.MovieRepositoryImpl
import com.example.myapplication.main.data.remote.retrofit.RetrofitClient
import com.example.myapplication.main.data.remote.retrofit.RetrofitClientImpl
import com.example.myapplication.main.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object DiModule {

    @JvmStatic
    fun getModules(): Module {
        return module {

            single<RetrofitClient> { RetrofitClientImpl() }
            single<MovieRepository> { MovieRepositoryImpl(get()) }

            viewModel { MovieViewModel(get()) }
        }
    }
}