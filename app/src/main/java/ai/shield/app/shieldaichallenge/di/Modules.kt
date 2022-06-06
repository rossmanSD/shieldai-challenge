package ai.shield.app.shieldaichallenge.di

import ai.shield.app.shieldaichallenge.repository.EpisodeRepository
import ai.shield.app.shieldaichallenge.viewmodel.ListItemViewModel
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(androidContext().packageName, Context.MODE_PRIVATE)
    }
    single { EpisodeRepository(get(), get()) }
    viewModel { ListItemViewModel(get()) }
}
