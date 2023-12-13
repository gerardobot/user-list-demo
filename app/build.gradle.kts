plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.userlist.config)
}

android {
    namespace = Config.appId
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
    }
}

kotlinter {
    disabledRules = Config.Kotlinter.disabledRules
}

val installKotlinterPrePushHook by tasks.creating(
    org.jmailen.gradle.kotlinter.tasks.InstallPrePushHookTask::class
)

tasks.preBuild {
    dependsOn("installKotlinterPrePushHook")
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data)
    implementation(projects.framework)
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.koin.compose)

    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.preview)
    implementation(libs.compose.ui)

    debugImplementation(libs.debug.compose.testManifest)
    debugImplementation(libs.debug.compose.tooling)

    testImplementation(libs.test.junit)
}