package ru.myitschool.nasa_bootcamp.utils

import kotlin.math.PI

const val TAG = "BERS"
const val NASA_BASE_URL = "https://api.nasa.gov/"
const val SPACEX_BASE_URL = "https://api.spacexdata.com/v3/"
const val SPACEX_BASE_V5_URL = "https://api.spacexdata.com/v5/"
const val NEWS_BASE_URL = "https://api.spaceflightnewsapi.net/v3/"
const val API_KEY = "uej4DeQlgTn9GRLfb98qSj38c2mecIuWspj3JyTN"
const val STARMAN_GIF_LINK = "https://i.pinimg.com/originals/3f/da/af/3fdaaf0000e825ee1477d6e0d30db980.gif"
const val wrongCredits = "invalidCredentials"
const val userAlreadyRegistered = "userAlreadyRegistered"
const val invalidEmail = "invalidEmail"
const val errorEmailIsNotVerified = "Your email is not verified. Click 'send' button to recieve an email!"

const val SEC_HOUR = 3600000L
const val DEGREES_TO_RADIANS = PI / 180.0
const val RADIANS_TO_DEGREES = 180.0 / PI
const val OBLIQUITY = 23.439281f * DEGREES_TO_RADIANS //Наклонность Земли
