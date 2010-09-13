#!/bin/bash

# Create XML-schema from class

# To be run in project dir
schemagen -d target/classes -cp src/main/java/edu/chl/johanssb/jaxb/Class4Schema.java

exit 0
