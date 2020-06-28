import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        jcenter()
        maven {
            url = uri("https://kotlin.bintray.com/ktor")
        }
    }

    dependencies {
        val kotlinVersion: String by project
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
    }
}

plugins {
    kotlin("jvm") version "1.3.72"
    application
}

application {
    mainClassName = "org.remo.AppKt"
}

group = "org.remo"
version = "1.0-SNAPSHOT"

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

repositories {
    mavenLocal()
    jcenter()
    maven {
        url = uri("https://kotlin.bintray.com/ktor")
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

dependencies {
    val ktorVersion: String by project
    val exposedVersion: String by project

    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-jackson:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
    implementation("mysql:mysql-connector-java:8.0.19")
    implementation("com.zaxxer:HikariCP:3.4.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    jar {
        manifest {
            attributes["Main-Class"] = "org.remo.AppKt"
        }
        from(
            configurations.runtimeClasspath.get().map {
                if (it.isDirectory) it else zipTree(it)
            }
        )
    }
}

