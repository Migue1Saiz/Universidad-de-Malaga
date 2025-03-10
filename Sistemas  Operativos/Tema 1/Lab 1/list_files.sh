 #!/bin/sh

for i in $*	# Iterate over the inputed elements
do
	echo -e '\n\n'	# Give double intros to make output pretty
	echo "    ---- ## `ls -d $i` ## ----"	# Format to show the name of the file
	cat $i	# Display the contents of the file, taking into account that it will tell what the inputed element is if it cannot display its contents
done

