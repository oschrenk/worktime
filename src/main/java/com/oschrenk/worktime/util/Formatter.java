package com.oschrenk.worktime.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class Formatter {

	public static DateTimeFormatter DATETIME_DAY_MONTH_YEAR = DateTimeFormat.forPattern("dd.MM.yyyy");

	public static DateTimeFormatter DATETIME_DAY_MONTH = DateTimeFormat.forPattern("dd.MM.");

	public static DateTimeFormatter DATETIME_HOUR_MINUTE = DateTimeFormat.forPattern("HH:mm");

	public static PeriodFormatter PERIOD_SHORT = new PeriodFormatterBuilder().appendDays().appendSuffix("d", "d").appendHours()
			.appendSuffix("h", "h").appendMinutes().appendSuffix("m", "m").appendSeconds().appendSuffix("s", "s").toFormatter();

}
