#!/bin/bash
source .env
mvn package -Dmaven.test.skip=true
docker build -t dj_backend .
