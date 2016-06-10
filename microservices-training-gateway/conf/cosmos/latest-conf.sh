#!/bin/sh
# $1 is <path to your certificate>:<password>
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/int/component/microservicestraininggateway/configuration -o conf-microservicestraininggateway-int.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/test/component/microservicestraininggateway/configuration -o conf-microservicestraininggateway-test.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/live/component/microservicestraininggateway/configuration -o conf-microservicestraininggateway-live.json
