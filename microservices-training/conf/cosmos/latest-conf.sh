#!/bin/sh
# $1 is <path to your certificate>:<password>
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/int/component/microservicestraining/configuration -o conf-microservicestraining-int.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/test/component/microservicestraining/configuration -o conf-microservicestraining-test.json
curl --cert $1 https://api.live.bbc.co.uk/cosmos/env/live/component/microservicestraining/configuration -o conf-microservicestraining-live.json
