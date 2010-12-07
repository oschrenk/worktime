/*
 *******************************************************************************
 * EarlierThanPredicate.java
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
package com.oschrenk.worktime.util;

import org.joda.time.DateTime;

import com.google.common.base.Predicate;
import com.oschrenk.worktime.core.Day;

/**
 * 
 * @author oliver.schrenk
 * @version $Id: $
 */
public class AfterDateTimePredicate implements Predicate<Day> {

	private final DateTime dateTime;

	public AfterDateTimePredicate(final DateTime dateTime) {
		this.dateTime = dateTime;

	}

	/*
	 * @see com.google.common.base.Predicate#apply(java.lang.Object)
	 */
	@Override
	public boolean apply(final Day day) {
		if (day.getDay().isAfter(dateTime)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "AfterDateTimePredicate [dateTime=" + dateTime + "]";
	}

}
