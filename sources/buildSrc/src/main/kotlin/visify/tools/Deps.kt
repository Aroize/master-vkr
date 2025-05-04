package visify.tools

import org.gradle.api.Action
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

fun DependencyHandlerScope.api(dep: Any) = add("api", dep)

fun DependencyHandlerScope.impl(dep: Any) = add("implementation", dep)

fun<T: Dependency> DependencyHandlerScope.impl(
    dep: T,
    dependencyConfiguration: Action<T>
) = addDependencyTo(this, "implementation", dep, dependencyConfiguration)

fun DependencyHandlerScope.testImpl(dep: Any) = add("testImplementation", dep)

fun DependencyHandlerScope.androidTestImpl(dep: Any) = add("androidTestImplementation", dep)

fun DependencyHandlerScope.debugImpl(dep: Any) = add("debugImplementation", dep)

fun DependencyHandlerScope.releaseImpl(dep: Any) = add("releaseImplementation", dep)

fun DependencyHandlerScope.coreDesugaring(dep: Any) = add("coreLibraryDesugaring", dep)

fun DependencyHandlerScope.visifyKapt(dep: Any) = add("kapt", dep)