plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlinKsp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib9)
    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.core11)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    testImplementation(libs.junit)
}