#!/bin/sh
# Make the /var/log directory executable (issues with this after bake)
chmod -R a+X /var/log
mkdir /var/log/microservicestrainingquery
mkdir -p /var/run/microservicestrainingquery/
