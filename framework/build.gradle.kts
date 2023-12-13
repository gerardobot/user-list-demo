plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.ksp)
    alias(libs.plugins.userlist.config)
}

android {
    namespace = "com.gerardo.userlist.framework"

    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        compose = true
    }
}

kotlinter {
    disabledRules = Config.Kotlinter.disabledRules
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)

    implementation(libs.coil)
    implementation(libs.coil.gif)

    implementation(libs.compose.ui)

    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    ksp(libs.moshi.ksp)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    debugImplementation(libs.debug.chucker)
    releaseImplementation(libs.release.chucker)

    testImplementation(libs.test.junit)
}
