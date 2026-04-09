import org.gradle.api.tasks.wrapper.Wrapper

plugins {
    kotlin("multiplatform") version "2.3.20" apply false
    kotlin("jvm") version "2.3.20" apply false
    kotlin("plugin.serialization") version "2.3.20" apply false
    kotlin("plugin.spring") version "2.3.20" apply false
    id("org.springframework.boot") version "4.0.5" apply false
}

group = "WoWSFT"
version = "0.10.10.0-1"

allprojects {
    group = rootProject.group
    version = rootProject.version
}

tasks.wrapper {
    gradleVersion = "9.3.0"
    distributionType = Wrapper.DistributionType.BIN
}
