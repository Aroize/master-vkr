package visify.tools

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import java.io.File

val Project.tools: File
    get() = File(rootDir, "tools")

val BaseExtension.optimizeFile: File
    get() = getDefaultProguardFile("proguard-android-optimize.txt")
