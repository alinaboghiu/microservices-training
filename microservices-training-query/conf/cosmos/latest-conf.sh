#!/bin/sh
# $1 is <path to your certificate>:<password>
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/int/component/microservicestrainingquery/configuration -o conf-microservicestrainingquery-int.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/test/component/microservicestrainingquery/configuration -o conf-microservicestrainingquery-test.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/live/component/microservicestrainingquery/configuration -o conf-microservicestrainingquery-live.json
