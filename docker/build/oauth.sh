#!/bin/bash

function progress() {
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

# Docker image prefix
REGPREFIX=registry.cn-beijing.aliyuncs.com/szmengran
VERSION=$INFRASTRUCTURE_VERSION

cd ../oauth
mvn package
progress "Building oauth image ..."
docker tag $(docker build -t ${REGPREFIX}/oauth -q .) ${REGPREFIX}/oauth:${VERSION}
cd -