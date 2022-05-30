import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.6.21"
}

val junitVersion = "5.8.1"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("kotlin")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17" // Ensure that Kotlin also targets the correct JVM (important for modules)
    }

    // https://stackoverflow.com/a/47669720
    tasks.compileKotlin {
        destinationDirectory.set(tasks.compileJava.get().destinationDirectory.get())
    }
    tasks.compileTestKotlin {
        destinationDirectory.set(tasks.compileTestJava.get().destinationDirectory.get())
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
    }

    // To ensure that we can run main methods from the IDE
    tasks.withType<JavaExec> {
        jvmArgs = listOf("--enable-preview")
    }

    group = "ch.ergon.modern.java"
    version = "1.0"

    dependencies {
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
        testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)

        testImplementation(group = "io.mockk", name = "mockk", version = "1.12.4")
    }

    tasks.test {
        useJUnitPlatform()
        jvmArgs = listOf("--enable-preview")
    }
}