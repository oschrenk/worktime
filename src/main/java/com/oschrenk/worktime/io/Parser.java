/*
 *******************************************************************************
 * Parser.java
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
package com.oschrenk.worktime.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import com.oschrenk.worktime.core.Day;
import com.oschrenk.worktime.core.Task;
import com.oschrenk.worktime.util.Formatter;

/**
 * 
 * @author oliver.schrenk
 * @version $Id: $
 */
public class Parser implements com.oschrenk.util.Parser<File, List<Day>> {

	private final List<Day> days;
	private final DateTime now;
	private final Pattern pattern;

	public Parser() {
		now = new DateTime();
		days = new LinkedList<Day>();
		pattern = Pattern.compile("[\\t\\r\\n]");
	}

	public List<Day> parse(File file) {

		final BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

		ParserState state = ParserState.START_OF_FILE;

		Day day = null;
		DateTime currentDay = null;
		DateTime start = null;
		DateTime end = null;
		String line;
		String task = null;

		try {
			readline: while ((line = br.readLine()) != null) {
				line = line.trim();

				// comment, ignore line
				if (line.startsWith("#")) {
					continue readline;
				}

				if (state == ParserState.START_OF_FILE || line.isEmpty()) {
					// add last completed day to list
					if (day != null) {
						days.add(day);
						day = null;
					}

					// start a new day
					state = ParserState.START_OF_DAY;
					continue readline;
				}

				final Scanner scanner = new Scanner(line).useDelimiter(pattern);
				if (state == ParserState.START_OF_DAY) {
					while (scanner.hasNext()) {
						final String s = scanner.next();
						if (s.contains(".")) {
							state = ParserState.START_OF_TIME;
							currentDay = Formatter.DATETIME_DAY_MONTH.parseDateTime(s.trim()).withYear(now.getYear());
							day = new Day(currentDay);
							start = null;
							end = null;
							task = null;
							continue readline;
						}
					}
				}

				if (state == ParserState.START_OF_TIME) {
					while (scanner.hasNext()) {
						final String s = scanner.next();
						if (state == ParserState.START_OF_TIME && s.contains(":")) {

							if (start == null) {
								start = getDateTime(currentDay, s);
							} else {
								end = getDateTime(currentDay, s);
								day.add(new Task(start, end, task));
								start = end;
							}
							state = ParserState.START_OF_TASK_DESCRIPTION;

							continue;
						}

						else if (state == ParserState.START_OF_TASK_DESCRIPTION) {
							state = ParserState.START_OF_TIME;
							task = s;
						}
					}
				}

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

		return days;
	}

	private DateTime getDateTime(DateTime currentDay, final String s) {
		DateTime start;
		start = Formatter.DATETIME_HOUR_MINUTE.parseDateTime(s.trim()).withYear(currentDay.getYear())
				.withMonthOfYear(currentDay.getMonthOfYear()).withDayOfMonth(currentDay.getDayOfMonth());
		return start;
	}
}
