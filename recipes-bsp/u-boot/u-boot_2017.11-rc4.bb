require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

# This revision corresponds to the tag "v2016.03"
# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "7a69604bce9a9a9476753af64e5a1870880c1333"

PV = "2017.11-rc4"

UBOOT_SUFFIX = "img"
SPL_BINARY = "SPL"

COMPATIBLE_MACHINE = "udooneo"

SRC_URI_append_udooneo = " \
        file://0001-ARM-udoo-neo-uEnv.txt-bootz-single-partition-config-v2017.11-rc4.patch \
        file://0002-udoo_neo.h-add-fallback-option-to-load-kernel-dtb-wi.patch \
"
