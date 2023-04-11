#!/bin/bash -ex

mvn -e -q clean
mvn -e -q compile
mvn -e -q -Dprism.order=sw exec:java
