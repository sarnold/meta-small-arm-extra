#!/usr/bin/env sh
#
# required input: list of service names

set -eu

export PATH="/usr/sbin:/sbin:/usr/bin:/bin"

failures=0
trap "((failures = failures + 1))" ERR

find_root_partnum()
{
    ROOT_DEVICE=$(swupdate -g)
    ROOT_PART_NAME=$(echo "$ROOT_DEVICE" | cut -d "/" -f 3)
    ROOT_PART_DEV=$(echo "$ROOT_PART_NAME" | cut -d "p" -f 1)
    ROOT_PART_NUM=$(cat /sys/block/${ROOT_PART_DEV}/${ROOT_PART_NAME}/partition)
    echo "$ROOT_PART_NUM"
}

echo "Checking services: $@"

for name in "$@"; do
    service "$name" status
done

partnum=$(find_root_partnum)

if [ $partnum -gt 1 ]; then
    fw_setenv rpipart "$partnum"
fi

if [ $failures -eq 0 ]; then
    echo "Success"
    fw_setenv ustate 0
else
    echo "Something went wrong"
    fw_setenv ustate 3
    exit 1
fi
