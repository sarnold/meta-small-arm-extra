# custom oe-based kernel for upstream plus RCN bb-kernel giant patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-bb.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           http://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://defconfig"

PATCHTOOL = "git"

BRANCH_VERSION = "4.6"
LINUX_VERSION = "${BRANCH_VERSION}.2"
RELEASE_TAG = "v${LINUX_VERSION}"
BB_VERSION = "bone3"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV_beaglebone = "${RELEASE_TAG}"
#SRCREV_beaglebone = "${AUTOREV}"
KBRANCH_beaglebone = "linux-4.6.y"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI[patch.md5sum] = "f6de36b3215daf396ca202113e2cd18b"
SRC_URI[patch.sha256sum] = "ca3041543137709efca353c0640100201b3f1f25620f766e6d5cf11dc68274ba"

