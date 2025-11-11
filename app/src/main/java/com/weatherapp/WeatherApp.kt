package com.weatherapp

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.weatherapp.ui.theme.WeatherAppTheme

class WeatherApp : Application() {
    val FLAGS = FLAG_ACTIVITY_SINGLE_TOP or // Não cria atividade se no topo
            FLAG_ACTIVITY_NEW_TASK or // Cria nova tarefa
            FLAG_ACTIVITY_CLEAR_TASK // Limpa o backstack
    override fun onCreate() {
        super.onCreate()
        Firebase.auth.addAuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) { goToMain()
            } else { goToLogin() }
        }
    }
    private fun goToMain() {
        this.startActivity( Intent(this, MainActivity::class.java).setFlags(FLAGS) )
    }
    private fun goToLogin() {
        this.startActivity( Intent(this, LoginActivity::class.java).setFlags(FLAGS) )
    }
}
