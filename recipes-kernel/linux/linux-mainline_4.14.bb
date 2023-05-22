FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

require recipes-kernel/linux/linux-mainline-common.inc

PACKAGE_ARCHS[vardeps] = "MACHINE"


KERNEL_DEVICETREE_espressobin = "marvell/armada-3720-espressobin.dtb"

BRANCH_VERSION = "4.14"
LINUX_VERSION = "${BRANCH_VERSION}.224"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

BRANCH = "linux-${BRANCH_VERSION}.y"
SRCREV = "1d177c0872ab99ac8d1fe09376a56c2911a837c0"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

SRC_URI:append = " \
    file://0001-ARM64-dts-marvell-espressobin-patch-rollup.patch \
    file://defconfig \
"
