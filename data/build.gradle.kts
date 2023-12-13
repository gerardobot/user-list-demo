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
    implementation(projects.domain)

    testImplementation(libs.test.junit)
}
