#!/bin/bash
while true	# Infinite loop
do
	date >> /tmp/users	# appends the current date to the end of the file that is pointing to by the path
	who | cut -d ' ' -f 1 | sort -u | wc -l >> /tmp/users # does a pipeline and the output of it is then appended to the file pointed by the path
	# Pileline explanation:
	# get the users that are currently using the system
	# get an array of strings from the string, which each element is separated by an space, and then get the first element of this array of strings, getting only the usernames
	# Then sort the usernames, removing duplicates
	sleep 12	# wait for 12 seconds
done
