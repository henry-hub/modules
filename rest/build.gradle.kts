description = "应用模块"

plugins {
    alias(ihub.plugins.boot)
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
}

iHubVerification {
    jacocoInstructionCoveredRatio.set("0.5")
    // 忽略启动类所在包路径测试覆盖率检查
    jacocoPackageExclusion.set("pub.ihub.demo.rest")
}
