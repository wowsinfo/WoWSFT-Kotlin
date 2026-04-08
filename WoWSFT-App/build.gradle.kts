import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.springframework.boot.gradle.tasks.bundling.BootWar

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("war")
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.5"))

    implementation(project(":WoWSFT-Shared"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework:spring-context-support")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation(kotlin("reflect"))
}

val profile = providers.gradleProperty("profile").orElse("dev")

kotlin {
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.add("-Xjsr305=strict")
        freeCompilerArgs.add("-Xannotation-default-target=param-property")
    }
}

springBoot {
    mainClass.set("WoWSFT.ApplicationKt")
}

sourceSets {
    named("main") {
        resources.srcDir("src/main/resources-${profile.get()}")
    }
}

tasks.named<BootWar>("bootWar") {
    mainClass.set("WoWSFT.ApplicationKt")

    from("src/main/ebextensions") {
        into(".ebextensions")
    }

    from("src/main/platform") {
        into(".platform")
    }
}
