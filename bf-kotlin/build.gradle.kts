import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.20"
}

group = "com.github.mvukic"
version = "1.0"

base {
    archivesName = "bf-interpreter"
}

kotlin {
    jvmToolchain(22)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.0")
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()

    }

    wrapper {
        gradleVersion = "8.10"
    }

    compileKotlin {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            optIn = listOf("kotlin.ExperimentalUnsignedTypes")
            jvmTarget = JvmTarget.JVM_22
        }
    }
}