import com.nishtahir.CargoBuildTask

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.mozillaRustAndroidGradle)
}

android {
    namespace = "com.example.rustandroidsandbox"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rustandroidsandbox"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        sourceSets["main"].kotlin {
            srcDir("src/main/kotlin")
        }
        jvmTarget = "1.8"
    }
    externalNativeBuild {
        cargo {
            module = "src/main/rust/hello_world"
            libname = "hello_world"
            targets = listOf("arm", "arm64", "x86", "x86_64")
            rustupChannel = "nightly"
            pythonCommand = "python3"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

tasks.preBuild.configure {
    dependsOn.add(tasks.withType(CargoBuildTask::class.java))
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
}