package com.rahul.newsapp.utils.connectivity.di

import android.content.Context
import com.rahul.newsapp.utils.connectivity.NetworkConnectivity
import com.rahul.newsapp.utils.connectivity.internal.NetworkConnectivityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DI module supplying network state dependency.
 *
 * Created by abrol at 29/09/24.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class Module {
    companion object {
        /**
         * Function provide a [NetworkConnectivity]
         *
         * @param context
         * @return [NetworkConnectivityImpl]
         */
        @Provides
        @Singleton
        fun bind(@ApplicationContext context: Context): NetworkConnectivity =
            NetworkConnectivityImpl(context = context)
    }
}
