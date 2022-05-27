plugins {
    java
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
    }

    group = "ch.ergon.modern.java"
    version = "1.0"
    
    dependencies { 
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
        testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
    }

    tasks.test {
        useJUnitPlatform()
    }
}