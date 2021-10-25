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
        const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Android.COMPOSE_VERSION}"
        const val ANDROIDX_LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:1.3.1"

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