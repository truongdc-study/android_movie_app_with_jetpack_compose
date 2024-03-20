object Builds {
    object Release {
        const val name = "release"
        const val isMinifyEnabled = true
        const val isShrinkResources = true
        const val isDebuggable = false
    }

    object Debug {
        const val name = "debug"
        const val isMinifyEnabled = false
        const val isShrinkResources = false
        const val isDebuggable = true
    }

    const val SHARED_DIMENSION = "Nimble_Surveys"

    object Flavors {
        const val DEV = "dev"
        const val PROD = "prod"
    }
}