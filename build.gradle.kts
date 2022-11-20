buildscript {
    // TODO: Unable to convert below code into KTS
//    ext {
//        compose_version = "1.2.0-rc02"
//    }
    dependencies {
        val nav_version = "2.5.2"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${nav_version}")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application").version("7.2.2").apply(false)
    id("com.android.library").version("7.2.2").apply(false)
    id("org.jetbrains.kotlin.android").version("1.6.21").apply(false)
    id("com.google.dagger.hilt.android").version("2.44").apply(false)
}

// TODO: Unable to convert this into KTS
//task clean(type: Delete)
//    delete rootProject.buildDir
//}