#!/bin/bash
sh ./build-docker/build-config.sh
sh ./build-docker/build-eureka.sh
sh ./build-docker/build-gateway.sh
sh ./build-docker/build-oauth.sh
