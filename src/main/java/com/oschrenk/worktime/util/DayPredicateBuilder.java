package com.oschrenk.worktime.util;

import java.util.Date;

import org.joda.time.DateTime;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.oschrenk.util.Builder;
import com.oschrenk.worktime.core.Day;

public class DayPredicateBuilder implements Builder<Predicate<Day>> {

	private Date from;
	private Date to;

	private DayPredicateBuilder() {
	}

	public static DayPredicateBuilder make() {
		return new DayPredicateBuilder();
	}

	public DayPredicateBuilder from(Date from) {
		this.from = from;
		return this;
	}

	public DayPredicateBuilder to(Date to) {
		this.to = to;
		return this;
	}

	@Override
	public Predicate<Day> build() {
		Predicate<Day> fromPredicate;
		if (from != null) {
			fromPredicate = new AfterDateTimePredicate(new DateTime(from));
		} else {
			fromPredicate = Predicates.alwaysTrue();
		}

		Predicate<Day> toPredicate;
		if (from != null) {
			toPredicate = new BeforeDateTimePredicate(new DateTime(to));
		} else {
			toPredicate = Predicates.alwaysTrue();
		}

		return Predicates.and(fromPredicate, toPredicate);
	}

}
