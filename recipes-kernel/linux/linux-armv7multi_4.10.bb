# custom oe-based kernel for upstream plus RCN kernel patch
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}/:"

include linux-armv7multi.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           https://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.xz;name=patch \
"

SRC_URI:append_udooneo = "file://0002-ARM-drm-armada-add-fix-for-build-error-missing-heade.patch \
                         file://defconfig \
"

SRC_URI:append_nitrogen6x = "file://0001-imx6q-nitrogen6_max.dts-enable-spidev-on-ecspi5-disa.patch \
                             file://defconfig \
"

PATCHTOOL = "git"

BRANCH_VERSION = "4.10"
LINUX_VERSION = "${BRANCH_VERSION}.17"
RELEASE_TAG = "v${LINUX_VERSION}"
# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.17"
BB_VERSION = "armv7-x2"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV = "${KERNEL_TAG}"
KBRANCH = "linux-${BRANCH_VERSION}.y"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

SRC_URI[patch.md5sum] = "d3cc2e71404c69782b0039cf493537ab"
SRC_URI[patch.sha256sum] = "fa034c210a1a0be32c40021c3cbfd2b84080c72eb31b06e67d1317bdaa6ac900"

