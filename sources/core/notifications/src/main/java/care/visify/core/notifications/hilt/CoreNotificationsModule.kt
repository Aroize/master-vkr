package care.visify.core.notifications.hilt

import android.content.Context
import care.visify.core.notifications.NotificationHelper
import care.visify.core.notifications.intent.PendingIntentProvider
import care.visify.core.notifications.push.base.NotificationService
import care.visify.core.notifications.push.base.PushManager
import care.visify.core.notifications.push.gms.FirebasePushManager
import care.visify.core.notifications.showing.NotificationShowingProxy
import care.visify.core.notifications.showing.NotificationShowingProxyImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationManagerModule {
    @Binds
    @Singleton
    abstract fun bindPushManager(
        impl: FirebasePushManager
    ): PushManager

    @Binds
    @Singleton
    abstract fun bindNotificationShowingProxy(
        impl: NotificationShowingProxyImpl
    ): NotificationShowingProxy
}

@Module
@InstallIn(SingletonComponent::class)
object NotificationHelperModule {
    @Provides
    @Singleton
    fun provideNotificationHelper(
        @ApplicationContext context: Context,
        startupProvider: PendingIntentProvider
    ): NotificationHelper = NotificationHelper(context, startupProvider)
}

@Module
@InstallIn(SingletonComponent::class)
object NotificationsServiceModule {
    @Provides
    @Singleton
    fun provideNotificationService(retrofit: Retrofit): NotificationService = retrofit.create()
}