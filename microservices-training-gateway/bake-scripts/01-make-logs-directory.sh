#!/bin/sh
# Make the /var/log directory executable (issues with this after bake)
chmod -R a+X /var/log
mkdir /var/log/microservicestraininggateway
mkdir -p /var/run/microservicestraininggateway/
