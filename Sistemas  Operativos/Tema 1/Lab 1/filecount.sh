#!/bin/bash
#

for i in *	# Iterate over all the elements of the current directory					
do 
	if test -d "$i" 	# Count all the elements inside the element if it is a directory
	then								
		FILES=$( ls -l "$i" | wc -l)	# Lists all the elements inside the directory and then pipes them into the next command that counts all the lines (elements) inside the given direcory
		FILES2=`expr $FILES - 1`	# Removes one to the count of files
		echo "$i: $FILES2"		# Prints the name of the element followed by the element count
	fi  	
done 
