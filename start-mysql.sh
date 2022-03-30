#!/bin/bash

docker run -p 127.0.0.1:13306:13306 -p 172.17.0.1:3306:3306 --name mariadb -e MARIADB_ROOT_PASSWORD=SalmonFishOil -d --restart=always mariadb:10