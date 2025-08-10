plugins {
    id("java")
    application
    id("com.gradleup.shadow") version "9.0.0-rc3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("commons-cli:commons-cli:1.9.0")
}

application {
    mainClass.set("ru.nsu.agudkov.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "ru.nsu.agudkov.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}