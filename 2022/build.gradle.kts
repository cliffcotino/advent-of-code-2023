plugins {
    id("aoc.antlr-conventions")
    id("aoc.kotlin-conventions")
}

dependencies {
    implementation(libs.kotlin.logging)
    implementation(libs.kotlin.test)
    implementation(project(":common"))
}
