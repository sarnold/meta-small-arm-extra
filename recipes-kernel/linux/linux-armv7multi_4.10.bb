# custom oe-based kernel for upstream plus RCN kernel patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-armv7multi.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=nitrogen6x \
           http://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://0001-imx6q-nitrogen6_max.dts-enable-spidev-on-ecspi5-disa.patch \
           file://defconfig"

PATCHTOOL = "git"

BRANCH_VERSION = "4.10"
LINUX_VERSION = "${BRANCH_VERSION}.1"
RELEASE_TAG = "v${LINUX_VERSION}"
# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.1"
BB_VERSION = "armv7-x1"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV_nitrogen6x = "${KERNEL_TAG}"
#SRCREV_nitrogen6x = "${AUTOREV}"
KBRANCH_nitrogen6x = "linux-${BRANCH_VERSION}.y"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

SRC_URI[patch.md5sum] = "5a8899a0771572d01257d93160a1e604"
SRC_URI[patch.sha256sum] = "b5f567152b010553563fc45f4251ee47ad3601594198f5f6671818d350f0820e"
