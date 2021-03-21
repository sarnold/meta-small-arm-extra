require recipes-kernel/linux/linux-mainline-common.inc

PACKAGE_ARCHS[vardeps] = "MACHINE"

KERNEL_DEVICETREE_espressobin = " \
    marvell/armada-3720-espressobin.dtb \
    marvell/armada-3720-espressobin-emmc.dtb \
"

KERNEL_DEVICETREE_espressobin-v7 = " \
    marvell/armada-3720-espressobin-v7.dtb \
    marvell/armada-3720-espressobin-v7-emmc.dtb \
"

KERNEL_DEVICETREE_espressobin-ultra = " \
    marvell/armada-3720-espressobin-ultra.dtb \
"

LINUX_VERSION = "5.10.14"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

BRANCH = "linux-5.10.y"
#SRCREV = "${AUTOREV}"
SRCREV = "b0c8835fc649454c33371f4617111cb5d60463e1"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

SRC_URI_append = " \
    file://0001-arm64-dts-marvell-add-DT-for-ESPRESSObin-Ultra.patch \
    file://defconfig \
"
