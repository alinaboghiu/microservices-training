#!/bin/sh
# $1 is <path to your certificate>:<password>
curl --cert $1 -H 'Content-Type: application/json' -X PUT https://api.live.bbc.co.uk/cosmos/env/$2/component/microservicestrainingquery/configuration -d@conf-microservicestrainingquery-$2.json
