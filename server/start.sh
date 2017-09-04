#!/bin/sh
mysql.server start
Zkserver start
kafka-server-start /usr/local/etc/kafka/server.properties
