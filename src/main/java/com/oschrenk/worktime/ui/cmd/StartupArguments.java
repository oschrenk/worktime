package com.oschrenk.worktime.ui.cmd;

import java.io.File;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;
import uk.co.flamingpenguin.jewel.cli.Unparsed;

@CommandLineInterface(application = "worktime")
public interface StartupArguments {

	@Unparsed(name = "FILE")
	File path();

	@Option(defaultValue = "1-1-1970")
	String from();

	@Option(defaultValue = "today")
	String to();

	@Option(defaultValue = "")
	String ignore();

}
