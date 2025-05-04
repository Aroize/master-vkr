package care.visify.core.navigator.impl.hilt

import care.visify.core.navigator.api.VisifyNavigatorTaskFactory
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.navigator.impl.VisifyRouterImpl
import care.visify.core.navigator.impl.bottomnav.BottomNavBlockingTask
import care.visify.core.navigator.impl.bottomnav.BottomNavStateHolder
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    fun provideNavigatorHolder(
        cicerone: Cicerone<Router>
    ): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideVisifyRouter(
        delegate: Router,
        factory: VisifyNavigatorTaskFactory
    ): VisifyRouter = VisifyRouterImpl(delegate, factory)

    @Provides
    @Singleton
    fun provideBottomNavTask(
        controller: BottomNavStateHolder
    ) = BottomNavBlockingTask(controller)

    @Provides
    @Singleton
    fun provideBottomNavController() = BottomNavStateHolder()
}