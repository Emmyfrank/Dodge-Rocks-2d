#!/bin/bash
set -e
cd "$(dirname "$0")"
export JAVA_HOME="${JAVA_HOME:-$(/usr/libexec/java_home 2>/dev/null)}"
mkdir -p out
javac -d out src/com/game/*.java
java -cp out com.game.Main
