package com.android.weathergojek

import android.app.Application
import com.android.weathergojek.data.di.ApiModule
import com.android.weathergojek.data.di.DataSourceModule
import com.android.weathergojek.data.di.RepositoryModule
import com.android.weathergojek.di.component.ActivityComponent
import com.android.weathergojek.di.component.ApplicationComponent
import com.android.weathergojek.di.component.DaggerActivityComponent
import com.android.weathergojek.di.component.DaggerApplicationComponent
import com.android.weathergojek.di.module.ApplicationModule
import com.android.weathergojek.di.module.MiscellaneousModule
import com.android.weathergojek.domain.di.UseCaseModule

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class App : Application() {
    private var applicationComponent: ApplicationComponent? = null
    private var activityComponent: ActivityComponent? = null
    private var applicationModule: ApplicationModule? = null
    private var miscellaneousModule: MiscellaneousModule? = null

    companion object {
        private var instance: App? = null

        private fun initAppComponent() {
            instance?.apply {
                if (applicationModule == null) applicationModule = ApplicationModule()
                if (miscellaneousModule == null) miscellaneousModule = MiscellaneousModule()
                activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(applicationComponent)
                    .miscellaneousModule(miscellaneousModule)
                    .build()
            }
        }

        fun getActivityComponent(): ActivityComponent? {
            initAppComponent()
            return instance?.activityComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent.builder()
            .apiModule(ApiModule(BuildConfig.API_URL, BuildConfig.DEBUG))
            .dataSourceModule(DataSourceModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()
        initAppComponent()
    }
}
