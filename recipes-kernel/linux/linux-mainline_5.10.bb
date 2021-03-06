FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

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

BRANCH_VERSION = "5.10"
LINUX_VERSION = "${BRANCH_VERSION}.14"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

BRANCH = "linux-${BRANCH_VERSION}.y"
SRCREV = "b0c8835fc649454c33371f4617111cb5d60463e1"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

SRC_URI_append = " \
    file://0001-arm64-dts-marvell-add-DT-for-ESPRESSObin-Ultra.patch \
    file://defconfig \
"
