/*
 *******************************************************************************
 * DailyHoursPrinter.java
 * $Id: $
 *
 *******************************************************************************
 *
 * Copyright:   Q2WEB GmbH
 *              quality to the web
 *
 *              Tel  : +49 (0) 211 / 159694-00	Kronprinzenstr. 82-84
 *              Fax  : +49 (0) 211 / 159694-09	40217 D�sseldorf
 *              eMail: info@q2web.de						http://www.q2web.de
 *
 *
 * Author:      oliver.schrenk
 *
 * Created:     06.12.2010
 *
 * Copyright (c) 2009 Q2WEB GmbH.
 * All rights reserved.
 *
 *******************************************************************************
 */
package com.oschrenk.worktime.ui.cmd;

import java.util.List;

import org.joda.time.Period;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.oschrenk.worktime.core.Day;
import com.oschrenk.worktime.core.Printer;
import com.oschrenk.worktime.core.Task;
import com.oschrenk.worktime.util.Formatter;

/**
 * 
 * @author oliver.schrenk
 * @version $Id: $
 */
public class DailyHoursByMonthPrinter implements Printer {

	private final Predicate<Day> dayPredicate;
	private final Predicate<Task> taskPredicate;

	public DailyHoursByMonthPrinter(final Predicate<Day> dayPredicate, final Predicate<Task> taskPredicate) {
		this.dayPredicate = dayPredicate;
		this.taskPredicate = taskPredicate;
	}

	public void print(final List<Day> days) {

		final Iterable<Day> filter = Iterables.filter(Iterables.reverse(days), dayPredicate);

		boolean firstMonth = true;
		int previousMonth = -1;
		Period hoursPerMonth = new Period();

		for (final Day day : filter) {
			final int currentMonth = day.getDay().getMonthOfYear();
			if (previousMonth != currentMonth) {
				if (firstMonth) {
					firstMonth = false;
					previousMonth = currentMonth;
				} else {
					if (hoursPerMonth.getMinutes() > 0) {
						hoursPerMonth = printHoursPerMonth(hoursPerMonth);
						previousMonth = currentMonth;
					}
				}
			}

			System.out.print(Formatter.DATETIME_DAY_MONTH_YEAR.print(day.getDay()));
			System.out.print(": ");
			Period hours = new Period();
			final Iterable<Task> tasks = Iterables.filter(day.getTasks(), taskPredicate);
			for (final Task task : tasks) {
				final Period period = new Period(task.getStart(), task.getEnd());
				hours = hours.plus(period);
			}
			hours = hours.normalizedStandard();
			hoursPerMonth = hoursPerMonth.plus(hours);
			System.out.println(Formatter.PERIOD_SHORT.print(hours));
		}
		printHoursPerMonth(hoursPerMonth);

	}

	/**
	 * @param hoursPerMonth
	 * @return
	 * @category action
	 */
	private Period printHoursPerMonth(Period hoursPerMonth) {
		System.out.println("----------------");
		System.out.println("           " + Formatter.PERIOD_SHORT.print(hoursPerMonth));
		System.out.println();
		hoursPerMonth = new Period();
		return hoursPerMonth;
	}
}
