package care.visify.business.core.navigator

import care.visify.core.navigator.api.VisifyNavigatorTaskFactory
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.navigator.impl.bottomnav.BottomNavStateHolder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {
    @Binds
    @Singleton
    abstract fun bindNavigatorTaskFactory(
        impl: BusinessNavigatorTaskFactory,
    ): VisifyNavigatorTaskFactory
}

@Module
@InstallIn(ActivityComponent::class)
object BottomNavModule {
    @Provides
    fun provideBottomNavViewController(
        scope: CoroutineScope,
        controller: BottomNavStateHolder,
        router: VisifyRouter,
    ): BusinessBottomNavViewController = BusinessBottomNavViewController(
        scope, controller, router
    )
}