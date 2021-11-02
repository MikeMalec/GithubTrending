plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.example.githubtrending"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.githubtrending.CustomTestRunner"
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
    sourceSets {
        getByName("androidTest").java.srcDirs("src/test-common/java")
        getByName("test").java.srcDirs("src/test-common/java")
    }
    buildFeatures {
        compose = true
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
    implementation(project(Project.Modules.BUSINESS))
    implementation(project(Project.Modules.FRAMEWORK))
    implementation(Project.Dependencies.ANDROIDX_CORE)
    implementation(Project.Dependencies.APPCOMPAT)
    implementation(Project.Dependencies.MATERIAL)
    implementation(Project.Dependencies.COMPOSE_UI)
    implementation(Project.Dependencies.COMPOSE_MATERIAL)
    implementation(Project.Dependencies.COMPOSE_TOOLING_PREVIEW)
    implementation(Project.Dependencies.ANDROIDX_LIFECYCLE)
    implementation(Project.Dependencies.COMPOSE_ACTIVITY)


    //Navigation
    implementation(Project.Dependencies.NAVIGATION)
    implementation(Project.Dependencies.NAVIGATION_ANIMATIONS)

    //Retrofit
    implementation(Project.Dependencies.RETROFIT)
    implementation(Project.Dependencies.RETROFIT_CONVERTER)

    //Room
    implementation(Project.Dependencies.ROOM)
    kapt(Project.Dependencies.ROOM_COMPILER)

    //dagger
    implementation(Project.Dependencies.HILT_ANDROID)
    implementation(Project.Dependencies.HILT_NAVIGATION)
    kapt(Project.Dependencies.DAGGER_HILT_COMPILER)
    implementation(Project.Dependencies.HILT_VIEW_MODEL_LIFECYCLE)
    kapt(Project.Dependencies.HILT_COMPILER)

    //Unit Tests
    implementation(Project.Dependencies.Tests.UnitTests.CORE)
    implementation(Project.Dependencies.Tests.UnitTests.JUNIT)
    implementation(Project.Dependencies.Tests.UnitTests.ARCH_CORE)
    implementation(Project.Dependencies.Tests.UnitTests.TRUTH)
    implementation(Project.Dependencies.Tests.UnitTests.MOCKITO)
    implementation(Project.Dependencies.Tests.UnitTests.COROUTINES)
    testImplementation(Project.Dependencies.Tests.UnitTests.JUNIT)

    //UI Tests
    androidTestImplementation(Project.Dependencies.Tests.UITests.ANDROIDX_JUNIT)

    androidTestImplementation(Project.Dependencies.Tests.UITests.UI_JUNIT)

    androidTestImplementation(Project.Dependencies.Tests.UITests.COMPOSE_MANIFEST)
    debugImplementation(Project.Dependencies.Tests.UITests.COMPOSE_UI_TOOLING)

    androidTestImplementation(Project.Dependencies.HILT_ANDROID_TEST)
    kaptAndroidTest(Project.Dependencies.HILT_COMPILER)
    androidTestImplementation(Project.Dependencies.Tests.UITests.RUNNER)

    //Coil
    implementation(Project.Dependencies.COIL)

    //Datastore
    implementation(Project.Dependencies.DataStore)
}