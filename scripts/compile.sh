#!/usr/bin/env bash
set -xeuo pipefail

./gradlew clean -Dhttp.proxyHost
./gradlew assembleRelease -Dhttp.proxyHost