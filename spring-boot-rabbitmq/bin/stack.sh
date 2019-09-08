#!/usr/bin/env bash

. .env

# stop all running containers
./bin/teardown.sh

set -e

# create a new stack
docker-compose -f ${INTEGRATION_PATH}/docker-compose.yml up