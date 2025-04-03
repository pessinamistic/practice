#!/bin/bash
mvn spotless:apply

java_files_modified=$(git status | grep "\.java")
if [ "$java_files_modified" != "" ]
then
  echo "$java_files_modified"
  git add .
fi