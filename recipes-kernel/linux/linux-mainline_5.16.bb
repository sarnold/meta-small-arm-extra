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

BRANCH_VERSION = "5.16"
LINUX_VERSION = "${BRANCH_VERSION}.20"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

BRANCH = "linux-${BRANCH_VERSION}.y"
SRCREV = "c19a885e12f114b799b5d0d877219f0695e0d4de"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

SRC_URI_append = " \
    file://defconfig \
"
