# custom oe-based kernel for upstream plus RCN kernel patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-armv7multi.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           https://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.xz;name=patch \
           file://defconfig \
"

PATCHTOOL = "git"

BRANCH_VERSION = "4.12"
LINUX_VERSION = "${BRANCH_VERSION}.14"
RELEASE_TAG = "v${LINUX_VERSION}"
# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.14"
BB_VERSION = "armv7-x3"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV = "${KERNEL_TAG}"
KBRANCH = "linux-${BRANCH_VERSION}.y"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

SRC_URI[patch.md5sum] = "84076270a0d1e26e206540b849858880"
SRC_URI[patch.sha256sum] = "324de8673f20052d930d6f3a9d70469c1db1b1c3ae48ad03d0353d87856d7781"

