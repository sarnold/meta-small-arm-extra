# custom oe-based kernel for upstream plus RCN kernel patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-armv7multi.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           https://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.xz;name=patch \
           file://defconfig \
"

PATCHTOOL = "git"

BRANCH_VERSION = "4.14"
LINUX_VERSION = "${BRANCH_VERSION}.1"
RELEASE_TAG = "v${LINUX_VERSION}"
# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.1"
BB_VERSION = "armv7-x1"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV = "${KERNEL_TAG}"
KBRANCH = "linux-${BRANCH_VERSION}.y"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

SRC_URI[patch.md5sum] = "2e4bc51b323993f30b3a38c00ca04a0b"
SRC_URI[patch.sha256sum] = "8dc91866fe6435f9f7937d1e09e03473b2514d282394aace761bb3e9feb6f758"

