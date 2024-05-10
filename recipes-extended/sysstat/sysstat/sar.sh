#!/bin/sh

cleanup() {
   echo "Terminating apps..."
   killall sar
   echo "Done"
   exit 0
}

trap cleanup INT TERM

[ -n "$SLEEP" ] || SLEEP=10
echo "Running: sar -r  ALL -u ALL -o $1 $2 $3 with sleep: $SLEEP"
/usr/libexec/sa/sa1 --boot

while true ; do
    /usr/bin/sar -r  ALL -u ALL -o $1 $2 $3 >/dev/null 2>&1
    sleep $SLEEP
done
