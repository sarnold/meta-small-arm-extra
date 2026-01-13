FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:beaglebone = " file://uEnv.txt"

UBOOT_ENV:beaglebone = "uEnv"
