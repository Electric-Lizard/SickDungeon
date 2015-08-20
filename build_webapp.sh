#!/bin/bash
cd client
mvn clean install gwt:compile
cd ../server
mvn clean package
