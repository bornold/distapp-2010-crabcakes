#!/bin/bash

# Create classes from XML-schema

# To be run in project dir
# po.xml is the XML schema
xjc src/main/resources/po.xsd -d src/main/java  -p edu.chl.johanssb.jaxb.po

exit 0
