plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.wordchainer.bot"
version = "1.0.0"

repositories {
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    // Default
    implementation(kotlin("stdlib"))
    val kotlinxGroup = "org.jetbrains.kotlinx"
    implementation(kotlinxGroup, "kotlinx-coroutines-core", "1.3.9-native-mt-2")
    implementation(kotlinxGroup, "kotlinx-coroutines-jdk8", "1.3.9-native-mt-2")
    implementation(kotlinxGroup, "kotlinx-serialization-core", "1.0.0-RC2")

    // Discord
    implementation("net.dv8tion", "JDA", "4.2.0_209")

    // Default Utils
    implementation("com.google.code.gson", "gson", "2.8.6")
    implementation("com.github.kimcore", "inko.kt", "1.0")
    implementation("com.github.kimcore", "Josa.kt", "1.4")

    //DB
    implementation("org.jetbrains.exposed", "exposed", "0.17.7")

    // Logging
    implementation("org.slf4j", "slf4j-api", "1.7.30")
    implementation("ch.qos.logback", "logback-classic", "1.2.3")
}

tasks {
    compileKotlin {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    compileTestKotlin {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    jar {
        exclude("natives")
        finalizedBy(shadowJar)
        manifest {
            attributes("Main-Class" to "com.wordchainer.bot.Main")
        }
    }
}