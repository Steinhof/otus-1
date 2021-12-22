import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    id("nu.studer.jooq") version "6.0.1"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("com.google.cloud.tools.jib") version "3.1.4"
}

group = "com.overlap"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    jooqGenerator("mysql:mysql-connector-java")
    runtimeOnly("mysql:mysql-connector-java")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
    dependsOn("generateJooq")
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    url = "jdbc:mysql://localhost:3306/otus"
                    user = "root"
                    password = "root"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        inputSchema = "otus"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "com.example.otus.database"
                        directory = "src/main/database"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

jib {
    to {
        image = "europe-north1-docker.pkg.dev/overlap-332908/overlap/otus:latest"
        tags = setOf("latest")
    }
    from {
        image = "openjdk:17-alpine"
    }
}
