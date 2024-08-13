package com.myaxa.cryptocurrencies_list.di

import android.app.Application
import com.myaxa.core.viewmodel_inject.UniversalViewModelFactoryModule
import com.myaxa.core.viewmodel_inject.ViewModelFactoryProvider
import com.myaxa.features.coin_details.di.CoinDetailsDepsModule
import com.myaxa.features.coin_list.di.CoinListDepsModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope


@Component(modules = [ApplicationModule::class])
@ApplicationScope
interface ApplicationComponent : ViewModelFactoryProvider {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Application,
        ): ApplicationComponent
    }
}

@Module(
    includes = [
        UniversalViewModelFactoryModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        CoinDepsModule::class,
        CoinListDepsModule::class,
        CoinDetailsDepsModule::class,
    ]
)
interface ApplicationModule

@Scope
annotation class ApplicationScope
