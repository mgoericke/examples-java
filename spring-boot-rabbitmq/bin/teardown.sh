#!/usr/bin/env bash

. .env

docker-compose -f ${INTEGRATION_PATH}/docker-compose.yml down