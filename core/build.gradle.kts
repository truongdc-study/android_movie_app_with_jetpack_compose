plugins {
    id(Plugins.ANDROID_LIB)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
    id(Plugins.KOTLIN_KAPT)
}

android {
    namespace = "${Configs.NAMSPACE}.core"
    compileSdk = Configs.COMPLIED_SDK

    defaultConfig {
        minSdk = Configs.MIN_SDK
        testInstrumentationRunner = Configs.ANDROID_JUNIT_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(Builds.Release.name) {
            isMinifyEnabled = Builds.Release.isMinifyEnabled
            proguardFiles(getDefaultProguardFile(Configs.PROGUARD_FILE), Configs.PROGUARD_RULES)
        }

        getByName(Builds.Debug.name) {
            isMinifyEnabled = Builds.Debug.isMinifyEnabled
        }
    }

    flavorDimensions += Builds.SHARED_DIMENSION
    productFlavors {
        create(Builds.Flavors.DEV) {
            buildConfigField("String", "BASE_API_URL", "\"https://api.themoviedb.org/3/\"")
        }

        create(Builds.Flavors.STG) {
            buildConfigField("String", "BASE_API_URL", "\"https://api.themoviedb.org/3/\"")
        }

        create(Builds.Flavors.PROD) {
            buildConfigField("String", "BASE_API_URL", "\"https://api.themoviedb.org/3/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(Libs.AndroidX.CORE_KTX)
    implementation(Libs.AndroidX.APP_COMPAT)

    testImplementation(platform(Libs.JUnit5.BOM))
    testImplementation(Libs.JUnit5.JUPITER)
    testImplementation(Libs.MOCKK)
    testImplementation(Libs.KOTEST)
    testImplementation(Libs.Kotlin.COROUTINES_TEST)
    androidTestImplementation(Libs.KOTEST)
    androidTestImplementation(Libs.TURBINE)
    androidTestImplementation(Libs.MOCKK_ANDROID)
    androidTestImplementation(Libs.Kotlin.COROUTINES_TEST)
    androidTestImplementation(Libs.AndroidX.TEST_JUNIT)
    androidTestImplementation(Libs.AndroidX.TEST_CORE_KTX)
    androidTestImplementation(Libs.AndroidX.TEST_ESPRESSO_CORE)

    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)

    implementation(Libs.Kotlin.COROUTINES_ANDROID)

    implementation(Libs.Retrofit.RETROFIT)
    implementation(Libs.Retrofit.RETROFIT_MOSHI)

    implementation(Libs.Moshi.MOSHI)
    implementation(Libs.Moshi.MOSHI_ADAPTER)
    implementation(Libs.Moshi.MOSHI_KOTLIN)

    implementation(Libs.LOGGING_INTERCEPTOR)
    implementation(Libs.CHUCKER)
    implementation(Libs.TIMBER)
}