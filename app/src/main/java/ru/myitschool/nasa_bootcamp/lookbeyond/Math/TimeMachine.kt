package ru.myitschool.nasa_bootcamp.lookbeyond.Math

import java.util.*
import kotlin.math.floor


object TimeMachine {
    fun julianCenturies(date: Date?): Double {
        val day = julianDay(date)
        val delta = day - 2451545.0
        return delta / 36525.0
    }

    // JD = 367 * Y - INT(7 * (Y + INT((M + 9)/12))/4) + INT(275 * M / 9 + D + 1721013.5 + UT/24
    fun julianDay(date: Date?): Double {
        val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
        cal.time = date
        val hour = (cal[Calendar.HOUR_OF_DAY] 
                + cal[Calendar.MINUTE] / 60.0 + cal[Calendar.SECOND] / 3600.0)
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH] + 1
        val day = cal[Calendar.DAY_OF_MONTH]
        
        return (367.0 * year - floor(7.0 * (year + floor((month + 9.0) / 12.0)) / 4.0)
                + floor(275.0 * month / 9.0) + day + 1721013.5 + hour / 24.0)
    }

    //Среднее звездное
    fun meanSiderealTime(date: Date?, longitude: Double): Double {
        val day = julianDay(date)
        val delta = day - 2451545.0f

        var result = 280.461f + 360.98564737f * delta + longitude % 360
        if (result < 0) result += 360.0
        return result
    }

}