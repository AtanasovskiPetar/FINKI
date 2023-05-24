#!/bin/bash
if [ "$#" -lt 1 ];
then
        echo "Add arguments"
        exit 1
fi
if [ ! -f "$1" ];
then
        echo "No such file exists"
        exit 1
fi
filenames " ls -l | grep ".*.txt$" | grep "^.rw." | awk '{print $10;}' "

for fil in filenames
do
   cat $fil >> $1
   echo '' >> $1
done