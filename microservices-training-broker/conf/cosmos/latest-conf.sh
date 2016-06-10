#!/bin/sh
# $1 is <path to your certificate>:<password>
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/int/component/microservicestrainingbroker/configuration -o conf-microservicestrainingbroker-int.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/test/component/microservicestrainingbroker/configuration -o conf-microservicestrainingbroker-test.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/live/component/microservicestrainingbroker/configuration -o conf-microservicestrainingbroker-live.json
