plugins {
    id("java")
}

group = "ru.novalserg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(group = "com.codeborne", name = "selenide", version = "7.9.4")
}

tasks.test {
    useJUnitPlatform()
}