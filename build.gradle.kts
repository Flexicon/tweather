plugins {
    val kotlinVersion = "1.8.21"
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
    application
}

group = "dev.flexicon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("dev.flexicon.tweather.MainKt")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:3.5.2")
    runtimeOnly("ch.qos.logback:logback-classic:1.4.6")

    val ktorVersion = "2.3.0"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
