plugins {
    java
    alias(libs.plugins.springBoot)
    alias(libs.plugins.dependencyManagement)
}

group = "br.com.brendosp"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springActuator)
    implementation(libs.springSecurity)
    implementation(libs.springValidation)
    implementation(libs.springWeb)
    implementation(libs.springDocOpenApi)
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testImplementation(libs.bundles.test)
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    runtimeOnly("org.postgresql:postgresql")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
