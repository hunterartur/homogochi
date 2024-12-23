#!/bin/bash

if [[ $(git tag) ]]; then
    echo $(git describe --tags --long)
else
    echo "0.0.0-$(git rev-parse --short HEAD)-notags"
fi

