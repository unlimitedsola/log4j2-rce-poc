plugins {
    kotlin("jvm") version "1.6.0"
    application
}

group = "com.example.log4j2"
version = "0.0.1-SNAPSHOT"

application {
    mainClass.set("com.example.log4j2.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.55")
    testImplementation(kotlin("test-junit5"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = "1.8"
    }
}
