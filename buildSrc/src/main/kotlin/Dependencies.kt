import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.3.71"

object GradlePlugins {
    object Versions {
        const val safeArgs = "2.2.1"
        const val androidGradle = "3.6.1"
    }

    const val android = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
}

object Dependencies {
    object Kotlin {
        object Versions {
            const val coreKtx = "1.2.0"
            const val fragmentKtx = "1.2.4"
        }
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    }

    object AndroidX {
        object Versions {
            const val appcompat = "1.1.0"
            const val material = "1.1.0"
            const val constraintLayout = "1.1.3"
            const val lifecycle = "2.2.0"
        }
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    }

    object Parsing {
        object Versions {
            const val moshi = "1.9.2"
        }
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Network {
        object Versions {
            const val okhttp3 = "4.6.0"
            const val retrofit2 = "2.8.1"
            const val retrofitCoroutines = "0.9.2"
            const val moshiRetrofit = "2.6.1"
        }
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
        const val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.moshiRetrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
        const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    }

    object MultiThreading {
        object Versions {
            const val coroutines = "1.3.6"
        }
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    fun DependencyHandler.implementationKotlin() {
        add("implementation", Kotlin.coreKtx)
        add("implementation", Kotlin.fragmentKtx)
        add("implementation", Kotlin.stdlib)
    }

    fun DependencyHandler.implementationAndroidX() {
        add("implementation", AndroidX.appcompat)
        add("implementation", AndroidX.material)
        add("implementation", AndroidX.constraintLayout)
        add("implementation", AndroidX.lifecycleExtensions)
    }

    fun DependencyHandler.implementationNetwork() {
        add("implementation", Network.retrofit)
        add("implementation", Network.retrofitMoshi)
        add("implementation", Network.retrofitCoroutines)
        add("implementation", Network.okhttp)
        add("implementation", Network.okhttpInterceptor)
    }

    fun DependencyHandler.implementationParsing() {
        add("implementation", Parsing.moshi)
        add("kapt", Parsing.moshiCodeGen)
    }

    fun DependencyHandler.implementationMultiThreading() {
        add("implementation", MultiThreading.coroutinesAndroid)
        add("implementation", MultiThreading.coroutinesCore)
    }
}