plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.userlist.config)
}

java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

kotlinter {
    disabledRules = Config.Kotlinter.disabledRules
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.test.kotlin)
    testImplementation(libs.test.kotlinx.coroutines)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockk)
}
