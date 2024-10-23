plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
    namespace = "edu.example.amv_movies_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.example.amv_movies_app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Propia
    implementation(libs.gson.serializer)
    /**
    Implementar librería que permite serializar y deserizalizar objetos a la hora de
    guardarlos recuperarlos en un archivo local (.xml, .txt)
     */

    // Librerías para interactuar con la API superhero.xml y enviar solicitudes de red
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.glide)
    implementation(libs.viewmodel.scope)

    implementation(libs.nav.fragment.ktx)
    implementation(libs.nav.ui)



    //Librerias para testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.framework)
    testImplementation(libs.mockito.junit)

    //Librerías para Android-Testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}