package com.darkwhite.yassirmoviesapp.utils

fun formatMinutesToHoursAndMinutes(minutes: Int): String {
  val hours = minutes / 60
  val remainingMinutes = minutes % 60
  return "${hours}h${remainingMinutes}m"
}