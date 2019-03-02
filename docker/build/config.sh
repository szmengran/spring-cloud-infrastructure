#!/bin/bash

function progress() {
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

# Docker image prefix
REGPREFIX=szmengran
VERSION=$INFRASTRUCTURE_VERSION

cd ../config
mvn package
progress "Building config image ..."
docker tag $(docker build -t ${REGPREFIX}/config -q .) ${REGPREFIX}/config:$INFRASTRUCTURE_VERSION
cd -
