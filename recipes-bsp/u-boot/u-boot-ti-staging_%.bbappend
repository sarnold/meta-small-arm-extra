FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://uEnv.txt"

UBOOT_ENV = "uEnv"

COMPATIBLE_MACHINE = "(beaglebone|am335x-evm)"
