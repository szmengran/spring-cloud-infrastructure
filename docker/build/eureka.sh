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

cd ../eureka
mvn package
progress "Building eureka image ..."
docker tag $(docker build -t ${REGPREFIX}/eureka -q .) ${REGPREFIX}/eureka:${VERSION}
cd -
