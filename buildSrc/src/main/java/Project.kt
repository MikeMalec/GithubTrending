object Project {

    object Android {
        const val COMPOSE_VERSION = "1.0.1"
    }

    object Dependencies {
        const val ANDROIDX_CORE = "androidx.core:core-ktx:1.6.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.3.1"
        const val MATERIAL = "com.google.android.material:material:1.4.0"
        const val COMPOSE_UI = "androidx.compose.ui:ui:${Android.COMPOSE_VERSION}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Android.COMPOSE_VERSION}"
        const val COMPOSE_TOOLING_PREVIEW =
            "androidx.compose.ui:ui-tooling-preview:${Android.COMPOSE_VERSION}"
        const val ANDROIDX_LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:1.3.1"

        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3"

        const val ROOM = "androidx.room:room-runtime:2.2.5"
        const val ROOM_COMPILER = "androidx.room:room-compiler:2.2.5"
        const val ROOM_COROUTINES = "androidx.room:room-ktx:2.3.0"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
        const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:2.9.0"

        const val HILT_VERSION = "2.35"
        const val HILT_ANDROID = "com.google.dagger:hilt-android:$HILT_VERSION"
        const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:$HILT_VERSION"

        const val HILT_VIEW_MODELS = "1.0.0-alpha03"
        const val HILT_VIEW_MODEL_LIFECYCLE =
            "androidx.hilt:hilt-lifecycle-viewmodel:$HILT_VIEW_MODELS"
        const val HILT_COMPILER = "androidx.hilt:hilt-compiler:$HILT_VIEW_MODELS"

        const val HILT_GRADLE = "com.google.dagger:hilt-android-gradle-plugin:2.36"

        object Tests {
            object UnitTests {
                const val JUNIT = "junit:junit:4.+"
            }

            object UITests {
                const val ANDROIDX_JUNIT = "androidx.test.ext:junit:1.1.3"
                const val ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
                const val UI_JUNIT = "androidx.compose.ui:ui-test-junit4:${Android.COMPOSE_VERSION}"
                const val COMPOSE_UI_TOOLING =
                    "androidx.compose.ui:ui-tooling:${Android.COMPOSE_VERSION}"
            }
        }
    }
}