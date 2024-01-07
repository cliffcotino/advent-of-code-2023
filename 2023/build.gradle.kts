plugins {
    id("aoc.kotlin-conventions")
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.logging)
    implementation(libs.kotlin.test)
    implementation(project(":common"))
}
