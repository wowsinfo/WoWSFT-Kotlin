import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31" apply false
    id("org.springframework.boot") version "2.5.4" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}
