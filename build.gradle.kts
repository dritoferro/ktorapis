import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.70"
    id("application")
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

group = "br.com.tagliaferrodev.ktor.rest"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_11

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

    // Web Framework
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")

    // Auth
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")

    // Database
    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("org.postgresql:postgresql:42.2.16")
    implementation("org.flywaydb:flyway-core:6.5.6")
    implementation("org.jetbrains.exposed:exposed-core:0.27.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.27.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.27.1")
    implementation("org.koin:koin-ktor:2.1.6")

    // Serialization
    implementation("io.ktor:ktor-jackson:$ktor_version")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.2")

    // Tests
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks {
    assemble { dependsOn(shadowJar) }

    application {
        mainClassName = "br.com.tagliaferrodev.ktor.rest.ApplicationKt"
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}