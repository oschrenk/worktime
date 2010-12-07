# README #

## Installation ##

	# get external dependencies
	git clone https://github.com/joestelmach/natty.git
	cd natty
	mvn clean install
	
	git clone https://github.com/oschrenk/worktime.git
	cd worktime
	mvn clean package
	chmod u+x worktime
	
## Usage ##

	./worktime /path/to/log.txt
	./worktime --from="last october" --ignore="Pause" ~/Downloads/logs/log.txt
	
## Format ##

The format is very strict. 

- no year
- dates have to be `dd.MM.`, no whitespace
- timestamps have to be `HH:mm`, followed by a tab
- task descriptions can't contain a dot
- each timestamp has to be followed by a task description, except the last when you finish
- each day has to be seperated by a newline

	14.08.
	9:00	Stuff
	10:00	Other Stuff
	11:00
	
	15.08.
	9:00	Things
	17:00