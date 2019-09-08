#!/usr/bin/env bash

INTEGRATION_PATH=./integration/
DB_PORT=5432
SERVICE_PORT=8001
. ${INTEGRATION_PATH}/_network_functions



(set -e
    ${INTEGRATION_PATH}/teardown.sh
)

(set -e
    docker-compose -f ${INTEGRATION_PATH}/docker-compose.yml up -d db
    _wait_for_listener localhost:${DB_PORT}
    echo "database started"
)

(set -e
    docker-compose -f ${INTEGRATION_PATH}/docker-compose.yml up --build -d springbootapp
    _wait_for_listener localhost:${SERVICE_PORT}
    echo "application started"
)
