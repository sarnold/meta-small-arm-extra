require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "4.14.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-4.14.y"
SRCREV = "${AUTOREV}"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

SRC_URI_append_espressobin = " \
    file://0001-ARM64-dts-marvell-espressobin-patch-rollup.patch \
    file://defconfig \
"

KERNEL_DEFCONFIG_espressobin = "${WORKDIR}/defconfig"
