require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

# This revision corresponds to the tag "v2018.11"
# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "0157013f4a4945bbdb70bb4d98d680e0845fd784"

PV = "2018.11"

UBOOT_SUFFIX = "img"
SPL_BINARY = "SPL"

COMPATIBLE_MACHINE = "(cubox-i|mx6)"

SRC_URI_append_udooneo = " \
        file://0001-ARM-udoo-neo-uEnv.txt-bootz-single-partition-config-v2017.11-rc4.patch \
        file://0002-udoo_neo.h-add-fallback-option-to-load-kernel-dtb-wi.patch \
"

SRC_URI_append_cubox-i = " \
        file://0001-mx6cuboxi-uEnv.txt-bootz-n-fixes-single-partition-un.patch \
"
