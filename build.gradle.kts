plugins {
	java
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"

}

group = "pe.edu.petcare"
version = "0.0.1-SNAPSHOT"
//


java {
	sourceCompatibility = JavaVersion.VERSION_17
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
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation ("javax.xml.bind:jaxb-api:2.3.1")
	implementation ("org.glassfish.jaxb:jaxb-runtime:2.3.1")

	//JUNIT
	testImplementation ("org.junit.jupiter:junit-jupiter:5.7.0")

	//MOCKITO
	testImplementation ("org.mockito:mockito-core:3.+")
	testImplementation ("org.mockito:mockito-junit-jupiter:3.+")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("javax.xml.bind:jaxb-api:2.3.0")




}


tasks.withType<Test> {
	useJUnitPlatform()
}
