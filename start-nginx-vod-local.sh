#!/bin/bash

docker run --name nginx-vod  -p 127.0.0.1:8082:8080 -v C:/Users/User/docker/videos:/opt/static/videos -v C:/Users/User/docker/config/nginx.conf:/usr/local/nginx/conf/nginx.conf -d nytimes/nginx-vod-module
