package com.oschrenk.worktime.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.oschrenk.util.Builder;
import com.oschrenk.worktime.core.Task;

public class TaskPredicateBuilder implements Builder<Predicate<Task>> {

	private String task;

	private TaskPredicateBuilder() {
	}

	public static TaskPredicateBuilder make() {
		return new TaskPredicateBuilder();
	}

	public TaskPredicateBuilder ignore(String task) {
		this.task = task;
		return this;
	}

	@Override
	public Predicate<Task> build() {
		if (task == null)
			return Predicates.alwaysTrue();

		task = task.trim();

		if (task.length() == 0)
			return Predicates.alwaysTrue();

		return Predicates.not(new TaskPredicate(task));
	}

}
