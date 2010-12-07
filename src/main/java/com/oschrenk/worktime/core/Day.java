/*
 *******************************************************************************
 * Day.java
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
package com.oschrenk.worktime.core;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

/**
 *
 * @author oliver.schrenk
 * @version $Id: $
 */
public class Day {

	private final DateTime day;
	private final List<Task> tasks;

	/**
	 * @param day
	 */
	public Day(final DateTime day) {
		this.day=day;
		tasks=new LinkedList<Task>();
	}

	public void add(final Task task) {
		tasks.add(task);
	}

	/**
	 * @return Returns the day.
	 * @category getter
	 */
	public DateTime getDay() {
		return day;
	}

	/**
	 * @return Returns the tasks.
	 * @category getter
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Day [day="+day+", tasks="+tasks+"]";
	}

}
