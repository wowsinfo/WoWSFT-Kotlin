import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-Xjsr305=strict")
        }
    }
    jvmToolchain(17)

    sourceSets {
        commonMain {
            kotlin.srcDir("src/commonMain/kotlin")
            dependencies {
                api(project(":wowsft-core"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
            }
        }
        jvmMain {
            kotlin.srcDir("src/main/kotlin")
            dependencies {
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.20.0")
            }
        }
    }
}
