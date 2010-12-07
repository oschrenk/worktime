/*
 *******************************************************************************
 * ParserState.java
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

/**
 *
 * @author oliver.schrenk
 * @version
 * $Id: $
 */
public enum ParserState {

	START_OF_FILE,
	START_OF_DAY,
	START_OF_TIME,
	START_OF_TASK_DESCRIPTION,

}
