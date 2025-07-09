#!/bin/bash
echo "Build libcocoainput for macOS"
mkdir -p commmon/src/main/resources/darwin
cd libcocoainput/darwin/libcocoainput
make && make install
