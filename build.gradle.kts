import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		maven("https://plugins.gradle.org/m2/")
	}

	dependencies {
		classpath("com.adarshr:gradle-test-logger-plugin:3.2.0")
	}
}

plugins {
	kotlin("jvm") version "1.7.10"
	id("com.adarshr.test-logger") version "3.2.0"
}

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
	testImplementation("org.amshove.kluent:kluent:1.68")
}

sourceSets {
	test {
		java {
			srcDirs.add(File("src/test"))
		}
	}
}

tasks.test {
	useJUnitPlatform()
	
	testLogging {
		events("failed")

		// log full stacktrace of failed test (assertion library descriptive error)
		exceptionFormat = TestExceptionFormat.FULL
	}
}

tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "11"
}
