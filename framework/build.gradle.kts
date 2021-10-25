plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose =  true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Project.Android.COMPOSE_VERSION
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(Project.Dependencies.ANDROIDX_CORE)
    implementation(Project.Dependencies.APPCOMPAT)
    implementation(Project.Dependencies.MATERIAL)
    implementation(Project.Dependencies.COMPOSE_UI)
    implementation(Project.Dependencies.COMPOSE_MATERIAL)
    implementation(Project.Dependencies.COMPOSE_TOOLING_PREVIEW)
    implementation(Project.Dependencies.ANDROIDX_LIFECYCLE)
    implementation(Project.Dependencies.COMPOSE_ACTIVITY)
    testImplementation(Project.Dependencies.Tests.UnitTests.JUNIT)
    androidTestImplementation(Project.Dependencies.Tests.UITests.ANDROIDX_JUNIT)
    androidTestImplementation(Project.Dependencies.Tests.UITests.ESPRESSO)
    androidTestImplementation(Project.Dependencies.Tests.UITests.UI_JUNIT)
    debugImplementation(Project.Dependencies.Tests.UITests.COMPOSE_UI_TOOLING)
}