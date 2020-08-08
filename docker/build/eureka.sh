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
VERSION=1.0.0.RELEASE

cd ../eureka
mvn package
progress "Building eureka image ..."
docker tag $(docker build -t ${REGPREFIX}/eureka -q .) ${REGPREFIX}/eureka:${VERSION}
cd -

docker push ${REGPREFIX}/eureka:${VERSION}