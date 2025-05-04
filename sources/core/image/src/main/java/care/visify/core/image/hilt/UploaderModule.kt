package care.visify.core.image.hilt

import care.visify.core.image.bitmap.BitmapImageLoader
import care.visify.core.image.bitmap.BitmapImageLoaderImpl
import care.visify.core.image.formatter.ImageUrlFormatter
import care.visify.core.image.formatter.ImageUrlFormatterImpl
import care.visify.core.image.upload.UploadImageHelper
import care.visify.core.image.upload.UploadImageManager
import care.visify.core.image.upload.UploadImageManagerImpl
import care.visify.core.image.upload.UploadImageRepository
import care.visify.core.image.upload.UploadImageRepositoryImpl
import care.visify.core.image.upload.UploadImageService
import care.visify.core.storage.ImageFileManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UploaderModule {

    @Binds
    @Singleton
    abstract fun bindUploadImageRepository(
        impl: UploadImageRepositoryImpl,
    ): UploadImageRepository

    @Binds
    @Singleton
    abstract fun bindUploadImageManager(
        impl: UploadImageManagerImpl,
    ): UploadImageManager

    @Binds
    @Singleton
    abstract fun bindBitmapImageLoader(
        impl: BitmapImageLoaderImpl,
    ): BitmapImageLoader

}

@Module
@InstallIn(SingletonComponent::class)
abstract class LoaderModule {
    @Binds
    @Singleton
    abstract fun bindImageUrlFormatter(
        impl: ImageUrlFormatterImpl,
    ): ImageUrlFormatter
}

@Module
@InstallIn(SingletonComponent::class)
object UploaderHelperModule {
    @Provides
    @Singleton
    fun provideUploadHelper(
        imageFileManager: ImageFileManager,
        uploadImageManager: UploadImageManager,
    ): UploadImageHelper = UploadImageHelper(imageFileManager, uploadImageManager)
}

@Module
@InstallIn(SingletonComponent::class)
object UploaderServiceModule {
    @Provides
    @Singleton
    fun provideUploadImageService(retrofit: Retrofit): UploadImageService = retrofit.create()
}