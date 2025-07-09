#!/bin/bash
echo "Build libcocoainput for Windows"
mkdir -p commmon/src/main/resources/win
cd libcocoainput/win
make && make install
