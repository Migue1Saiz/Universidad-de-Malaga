# Use: graph.sh <list_of_numbers>  
# example: graph.sh 1 3 5 6 7 5 6 4 3 2 2 1

for datum in $*	# Iterates over the elements that were entered
do 	
	if [[ $datum -lt 1 ]] || [[ $datum -gt 75 ]]	# If the number is not in range(1, 75) do
		then echo "Arguments must be between 1 and 75"	# Comunicate that it is not in the range
		exit	# stop executing more lines
	fi
done
clear 
for datum in $*	# Iterate over the elements that were entered
do
  n=0	# Initiate the variable that counts the number of stars
  until [[ $n -eq $datum ]]	# do while the number of stars is not equal to datum
  do
     echo -e "*\c"	# Print a star
     let n+=1	# sum 1 to the control variable
  done
  echo
done
