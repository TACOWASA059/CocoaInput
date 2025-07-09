#!/bin/bash
echo "Build libcocoainput for X11"
mkdir -p commmon/src/main/resources/x11
cd libcocoainput/x11
make clean
make && make install
