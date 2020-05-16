#!/bin/bash
DOCKER_IMAGE=@docker.image@
DOCKER_CONTAINER=@docker.container@
PROJECT_PORT=@project.port@
DOCKER_PROT=@docker.port@
PROJECT_LOG=@project.log@
DOCKER_LOG=@docker.log@
PROJECT_DIR=@project.dir@
DOCKER_DIR=@docker.dir@
PROJECT_HOSTNAME=@project.hostname@
docker stop ${DOCKER_CONTAINER}
docker rm  ${DOCKER_CONTAINER}
docker rmi  ${DOCKER_IMAGE}
docker build -t ${DOCKER_IMAGE} ./
docker run --name ${DOCKER_CONTAINER} --hostname ${PROJECT_HOSTNAME} --restart=always --privileged=true -p ${DOCKER_PROT}:${PROJECT_PORT} -v ${DOCKER_LOG}:${PROJECT_LOG} -v ${DOCKER_DIR}:${PROJECT_DIR} -dit ${DOCKER_IMAGE}
