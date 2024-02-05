import net.bytebuddy.build.gradle.AbstractByteBuddyTask
import net.bytebuddy.build.gradle.Adjustment
import pub.ihub.integration.bytebuddy.IHubControllerDocPlugin
import pub.ihub.integration.bytebuddy.IHubRecordDocPlugin
import pub.ihub.integration.bytebuddy.IHubEntityDocPlugin

description = "应用模块"

buildscript {
    dependencies {
        classpath("pub.ihub.integration:ihub-bytebuddy-plugin:main-SNAPSHOT")
        classpath("org.projectlombok:lombok:1.18.20")
    }
}

plugins {
    alias(ihub.plugins.boot)
    alias(ihub.plugins.integration)
    alias(ihub.plugins.javaagent)
}

dependencies {
//    implementation(project(":client"))
//    implementation(project(":service"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("org.springframework.modulith:spring-modulith-starter-jpa")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")

    runtimeOnly("org.springframework.modulith:spring-modulith-actuator")
    runtimeOnly("org.springframework.modulith:spring-modulith-observability")

    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")

    implementation("org.springframework.boot:spring-boot-docker-compose")

    runtimeOnly("com.h2database:h2")
    implementation("org.mapstruct:mapstruct")
    annotationProcessor("org.mapstruct:mapstruct-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")

//    implementation("pub.ihub.lib:ihub-process-doc:main-SNAPSHOT")
//    annotationProcessor("pub.ihub.lib:ihub-process-doc:main-SNAPSHOT")
}

iHubJavaagent {
    javaagent = "pub.ihub.integration:ihub-agent:main-SNAPSHOT"
}

byteBuddy {
//extensions.configure(AbstractByteBuddyTaskExtension::class.java) {
    transformation {
        plugin = IHubControllerDocPlugin::class.java
    }
    transformation {
        plugin = IHubRecordDocPlugin::class.java
    }
    transformation {
        plugin = IHubEntityDocPlugin::class.java
    }
//    discovery = Discovery.UNIQUE
//    discoverySet = files("META-INF/net.bytebuddy/build.plugins")
    adjustment = Adjustment.ACTIVE
}

tasks.withType(AbstractByteBuddyTask::class.java).configureEach {
    tasks.getByName("classes").dependsOn(this)
}
