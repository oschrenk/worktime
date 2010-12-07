package com.oschrenk.worktime.ui.cmd;

import java.util.Date;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.Cli;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

import com.google.common.base.Predicate;
import com.joestelmach.natty.Parser;
import com.oschrenk.worktime.core.Day;
import com.oschrenk.worktime.core.Task;
import com.oschrenk.worktime.util.DayPredicateBuilder;
import com.oschrenk.worktime.util.TaskPredicateBuilder;

public class CommandLine {

	public static void main(String[] args) {
		for (String string : args) {
			System.out.println(string);
		}
		
		Cli<StartupArguments> cli = null;
		StartupArguments parsedArguments;
		try {
			cli = CliFactory.createCli(StartupArguments.class);
			parsedArguments = cli.parseArguments(args);
		} catch (ArgumentValidationException e) {
			System.err.println(e);
			return;
		}
		com.joestelmach.natty.Parser dateParser = new Parser();
		com.oschrenk.worktime.io.Parser worktimeParser = new com.oschrenk.worktime.io.Parser();

		try {

			Date from = dateParser.parse(parsedArguments.from()).getDates().get(0);
			Date to = dateParser.parse(parsedArguments.to()).getDates().get(0);

			Predicate<Day> dayPredicate = DayPredicateBuilder.make().from(from).to(to).build();
			Predicate<Task> taskPredicate = TaskPredicateBuilder.make().ignore(parsedArguments.ignore()).build();

			new DailyHoursByMonthPrinter(dayPredicate, taskPredicate).print(worktimeParser.parse(parsedArguments.path()));

		} catch (IllegalArgumentException e) {
			System.err.println(e);
			return;
		}

	}
}
