#!/bin/bash

if [$(grep -o $USERNAME /etc/groups || wc -l) -lt 1]; 
then
	echo "no"
	exit 1
else
	echo "yes"
	exit 0
fi
