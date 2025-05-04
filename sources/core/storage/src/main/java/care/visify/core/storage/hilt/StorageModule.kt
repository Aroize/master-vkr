package care.visify.core.storage.hilt

import care.visify.core.storage.FileManager
import care.visify.core.storage.FileManagerImpl
import care.visify.core.storage.ImageFileManager
import care.visify.core.storage.ImageFileManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {
    @Binds
    @Singleton
    abstract fun bindFileManager(
        impl: FileManagerImpl
    ): FileManager

    @Binds
    @Singleton
    abstract fun bindImageFileManager(
        impl: ImageFileManagerImpl
    ): ImageFileManager
}