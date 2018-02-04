#!/bin/bash

open -a XQuartz \
	&& IP=$(ifconfig en0 | grep inet | awk '$1=="inet" {print $2}') \
	&& PORT_NUM=$(ps -ef | grep "Xquartz :\d" | grep -v xinit | awk '{ print $9; }') \
	&& /opt/X11/bin/xhost + $IP \
	&& DISPLAY=$IP$PORT_NUM docker-compose "$@"