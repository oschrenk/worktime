/*
 *******************************************************************************
 * Segment.java
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

import org.joda.time.DateTime;

/**
 *
 * @author oliver.schrenk
 * @version $Id: $
 */
public class Task {

	private final DateTime start;
	private final DateTime end;
	private final String task;

	/**
	 * @param start
	 * @param end
	 */
	public Task(final DateTime start, final DateTime end, final String task) {
		this.start=start;
		this.end=end;
		this.task=task;
	}

	/**
	 * @return Returns the start.
	 * @category getter
	 */
	public DateTime getStart() {
		return start;
	}

	/**
	 * @return Returns the end.
	 * @category getter
	 */
	public DateTime getEnd() {
		return end;
	}

	/**
	 * @return Returns the task.
	 * @category getter
	 */
	public String getTask() {
		return task;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task [start="+start+", end="+end+", task="+task+"]";
	}

}
