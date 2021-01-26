#!/bin/bash

if [[ $(($#)) -ne 1 ]]
then
	echo "you did not pass a parameter"
else
	file=$1

	while read line
	do
		echo "$line"
		echo
	done <$file

fi
