plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.gradle)
}

gradlePlugin {
    plugins.register("userlist-config") {
        id = "userlist-config"
        version = "1.0"
        implementationClass = "ConfigPlugin"
    }
}
