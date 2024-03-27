plugins {
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.ANDROID_APP)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT_ANDROID)
}

android {
    namespace = Configs.NAMSPACE
    compileSdk = Configs.COMPLIED_SDK

    defaultConfig {
        applicationId = Configs.APP_ID
        minSdk = Configs.MIN_SDK
        targetSdk = Configs.TARGET_SDK
        versionCode = Configs.VERSION_CODE
        versionName = Configs.VERSION_NAME

        testInstrumentationRunner = Configs.ANDROID_JUNIT_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName(Builds.Release.name) {
            isMinifyEnabled = Builds.Release.isMinifyEnabled
            isShrinkResources = Builds.Release.isShrinkResources
            signingConfig = signingConfigs[Builds.Debug.name]
            isDebuggable = Builds.Release.isDebuggable
            proguardFiles(getDefaultProguardFile(Configs.PROGUARD_FILE), Configs.PROGUARD_RULES)
        }

        getByName(Builds.Debug.name) {
            isMinifyEnabled = Builds.Debug.isMinifyEnabled
            isShrinkResources = Builds.Debug.isShrinkResources
            signingConfig = signingConfigs[Builds.Debug.name]
            isDebuggable = Builds.Debug.isDebuggable
            proguardFiles(getDefaultProguardFile(Configs.PROGUARD_FILE), Configs.PROGUARD_RULES)
        }
    }

    flavorDimensions += Builds.SHARED_DIMENSION
    productFlavors {
        create(Builds.Flavors.DEV) {
            applicationIdSuffix = ".${Builds.Flavors.DEV}"
            resValue("string", "app_name", "\"Base Dev\"")
        }

        create(Builds.Flavors.STG) {
            applicationIdSuffix = ".${Builds.Flavors.STG}"
            resValue("string", "app_name", "\"Base Stg\"")
        }

        create(Builds.Flavors.PROD) {
            resValue("string", "app_name", "\"Android Base\"")
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
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(Libs.AndroidX.CORE_KTX)
    implementation(Libs.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Libs.AndroidX.ACTIVITY_COMPOSE)
    implementation(platform(Libs.AndroidX.COMPOSE_BOM))
    implementation(Libs.AndroidX.COMPOSE_UI)
    implementation(Libs.AndroidX.COMPOSE_UI_GRAPHICS)
    implementation(Libs.AndroidX.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Libs.Material.MATERIAL_3_COMPOSE)
    implementation(Libs.AndroidX.APP_COMPAT)
    implementation(Libs.Material.MATERIAL)
    implementation(Libs.AndroidX.ACTIVITY_COMPOSE)
    implementation(Libs.CONSTRAIN_LAYOUT)

    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.AndroidX.TEST_JUNIT)
    androidTestImplementation(Libs.AndroidX.TEST_ESPRESSO_CORE)
    androidTestImplementation(Libs.AndroidX.COMPOSE_UI_TEST_JUNIT4)
    androidTestImplementation(platform(Libs.AndroidX.COMPOSE_BOM))
    debugImplementation(Libs.AndroidX.COMPOSE_UI_TOOLING)
    debugImplementation(Libs.AndroidX.COMPOSE_UI_TEST_MANIFEST)

    implementation(Libs.AndroidX.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Libs.AndroidX.NAV_COMPOSE)
    implementation(Libs.Material.MATERIAL_COMPOSE)
    implementation(Libs.FRAGMENT)

    implementation(Libs.Glide.GLIDE)
    implementation(Libs.Glide.GLIDE_COMPILE)

    implementation(Libs.Hilt.ANDROID)
    implementation(Libs.Hilt.NAV_COMPOSE)
    kapt(Libs.Hilt.COMPILER)

    implementation(Libs.Kotlin.COROUTINES_ANDROID)

    implementation(Libs.COIL_COMPOSE)

    implementation(Libs.TIMBER)

    implementation(Libs.Paging.PAGING_COMMON)
    implementation(Libs.Paging.PAGING_COMPOSE)
    implementation(Libs.Paging.PAGING_TEST)
    implementation(Libs.Paging.PAGING_RUNTIME)

    debugImplementation(Libs.LEAK_CANARY)
}