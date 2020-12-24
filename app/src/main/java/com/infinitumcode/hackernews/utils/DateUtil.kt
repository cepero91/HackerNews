package com.infinitumcode.hackernews.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateUtil {
    companion object {
        fun fromStringToLong(dateStr: String): Long {
            return DateTime.parse(
                dateStr,
                DateTimeFormat.forPattern(API_DEFAULT_DATE_FORMAT).withZoneUTC() // 2020-12-23T04:21:11.000Z
            ).toDateTime().millis
        }

        fun fromStringToDateTime(dateStr: String): DateTime {
            return DateTime.parse(
                dateStr,
                DateTimeFormat.forPattern(API_DEFAULT_DATE_FORMAT).withZoneUTC() // 2020-12-23T04:21:11.000Z
            ).toDateTime()
        }
    }
}