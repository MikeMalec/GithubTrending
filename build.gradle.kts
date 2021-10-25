buildscript {
    val kotlin_version by extra("1.5.21")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            setUrl("https://www.jitpack.io")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath(Project.Dependencies.HILT_GRADLE)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}