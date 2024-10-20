package edu.example.amv_movies_app.app.domain

sealed class ErrorApp{
    object InternetError: ErrorApp()
    object UnknownError: ErrorApp()
    object DatabaseError: ErrorApp()

}