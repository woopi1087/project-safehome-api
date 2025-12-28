plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.8"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.woopi"
version = "0.0.1-SNAPSHOT"
description = "woopi's project, safehome"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

val commonLang3Version = "3.18.0"
val springdocVersion = "2.8.9"
val kotestVersion = "5.9.1"
val kotestSpringExtensionVersion = "1.3.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Apache Commons Lang
	implementation("org.apache.commons:commons-lang3:$commonLang3Version")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")

	// h2 database
	runtimeOnly("com.h2database:h2")

//	// MariaDB JDBC
//	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")

	// kotest
	testImplementation(platform("io.kotest:kotest-bom:$kotestVersion"))
	testImplementation("io.kotest:kotest-framework-engine")
	testImplementation("io.kotest:kotest-framework-api")
	testImplementation("io.kotest:kotest-runner-junit5")
	testImplementation("io.kotest:kotest-assertions-core")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:$kotestSpringExtensionVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
