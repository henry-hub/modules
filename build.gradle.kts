plugins {
    id("pub.ihub.plugin")
    id("pub.ihub.plugin.ihub-git-hooks")
    id("pub.ihub.plugin.ihub-java") apply false
    id("pub.ihub.plugin.ihub-test") apply false
    id("pub.ihub.plugin.ihub-verification") apply false
    id("pub.ihub.plugin.ihub-publish") apply false
    id("pub.ihub.plugin.ihub-boot") apply false
}

subprojects {
    !project.pluginManager.hasPlugin("java-platform") || return@subprojects
    apply {
        plugin("pub.ihub.plugin.ihub-java")
        plugin("pub.ihub.plugin.ihub-test")
        plugin("pub.ihub.plugin.ihub-verification")
    }

    iHubBom {
        importBoms {
            group("pub.ihub.lib").module("ihub-dependencies").version("main-SNAPSHOT")
        }
    }

//    dependencies {
//        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
//    }
}

iHubGitHooks {
    hooks.set(mapOf(
        "pre-commit" to "./gradlew build",
        "commit-msg" to "./gradlew commitCheck"
    ))
}
