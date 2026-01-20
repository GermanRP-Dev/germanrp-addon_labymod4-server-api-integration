plugins {
    id("java")
    id("maven-publish")
}

group = "eu.germanrp"
version = System.getenv("VERSION") ?: "0.0.0"

repositories {
    mavenCentral()
    maven("https://dist.labymod.net/api/v1/maven/release/")
}

dependencies {
    compileOnly(libs.jetbrains.annotations)
    compileOnly(libs.labymod.serverapi.core)
    compileOnly(libs.google.autoService)
    compileOnly(libs.lombok)

    annotationProcessor(libs.google.autoService)
    annotationProcessor(libs.lombok)

    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
        }
    }

    repositories {
        maven("https://maven.pkg.github.com/GermanRP-Dev/germanrp-addon_labymod4-server-api-integration") {
            name = "GitHubPackages"
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}