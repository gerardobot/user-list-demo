import org.gradle.api.JavaVersion

@Suppress("unused")
object Config {
    const val appId = "com.gerardo.userlist"

    const val compileSdk = 34
    const val minSdk = 26
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"

    const val kotlinCompilerExtensionVersion = "1.5.6"

    object Kotlinter {
        val disabledRules = arrayOf("no-wildcard-imports")
    }
}
