#!/usr/bin/env bash

thrift --gen java -out src/main/java/ src/main/thrift/Twitter.thrift