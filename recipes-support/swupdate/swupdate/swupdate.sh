#!/bin/sh
#
# SWUpdate is configured with /etc/swupdate.cfg but things like the www
# server and downloader require commandline arg to actually enable the
# feature in the cfg file, eg, the downloader can be enabled below using
# -d with an empty set of arguments: swupdate -d ""
# BUT to use custom download we need to start our own init script instead.

exec /usr/bin/swupdate
