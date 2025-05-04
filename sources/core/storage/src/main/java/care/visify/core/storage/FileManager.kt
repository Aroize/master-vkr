package care.visify.core.storage

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import care.visify.core.ktx.CoroutineDispatchers
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

interface FileManager {
    enum class Scope {
        IMAGE
    }

    fun space(): Long

    fun scopedDirectory(scope: Scope): File

    suspend fun createFile(uri: Uri,
                           scope: Scope,
                           filename: String): File

    fun createFile(scope: Scope, filename: String): File
}

@Suppress("BlockingMethodInNonBlockingContext")
class FileManagerImpl @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    @ApplicationContext private val context: Context
): FileManager {

    private val rootDir = context.filesDir

    private val scopes: Map<FileManager.Scope, File> = mapOf(
        FileManager.Scope.IMAGE to File(rootDir, "internal_images")
    )

    init {
        scopes.values.forEach { scopedDir ->
            if (scopedDir.exists().not()) {
                require(scopedDir.mkdir())
            }
        }
    }

    override fun space(): Long = rootDir.totalSpace

    override fun scopedDirectory(scope: FileManager.Scope): File {
        return scopes[scope] ?: throw IllegalArgumentException("No directory for such scope = $scope")
    }

    override suspend fun createFile(
        uri: Uri,
        scope: FileManager.Scope,
        filename: String
    ): File {
        val resolver = context.contentResolver
        val root = scopedDirectory(scope)
        val buffer = ByteArray(BUFFER_SIZE)

        return withContext(dispatchers.io) {
            val result = File(root, filename)
            require(result.createNewFile())
            FileOutputStream(result).use { output ->
                resolver.openInputStream(uri)?.use { input ->
                    while (input.read(buffer) > 0) {
                        output.write(buffer)
                    }
                }
            }
            result
        }
    }

    override fun createFile(scope: FileManager.Scope, filename: String): File {
        val root = scopedDirectory(scope)
        val result = File(root, filename)
        require(result.createNewFile())
        return result
    }

    companion object {
        private const val BUFFER_SIZE = 1024
    }
}