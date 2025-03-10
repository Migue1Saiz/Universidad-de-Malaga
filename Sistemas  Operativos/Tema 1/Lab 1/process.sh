STOPNAME="stop_`basename $0`.$$"	# Generate the name of the file, its syntax is stop_ + 'The name of the file(in this case process.sh' + . + 'the process id' 
echo "kill $$; rm $STOPNAME" > $STOPNAME	# write into the file kill 'process id', and rm 'the filename' which ends the process that is being run in this file and then removes the file created
chmod +x $STOPNAME	# makes the created file executable by users, group and others
sleep 30	# wait 30 seconds
