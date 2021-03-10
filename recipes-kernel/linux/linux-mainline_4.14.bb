require recipes-kernel/linux/linux-mainline-common.inc

KERNEL_VERSION_SANITY_SKIP="1"
BB_GIT_SHALLOW_DEPTH = "1"

LINUX_VERSION = "4.14.224"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

BRANCH = "linux-4.14.y"
#SRCREV = "${AUTOREV}"
SRCREV = "1d177c0872ab99ac8d1fe09376a56c2911a837c0"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

SRC_URI_append_espressobin = " \
    file://0001-ARM64-dts-marvell-espressobin-patch-rollup.patch \
    file://defconfig \
"
