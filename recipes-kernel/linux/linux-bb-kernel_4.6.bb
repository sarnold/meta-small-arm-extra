# custom oe-based kernel for upstream plus RCN bb-kernel giant patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${LINUX_VERSION}:"

include linux-bb.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           http://rcn-ee.net/deb/xenial-armhf/v${LINUX_VERSION}.0-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://defconfig"

LINUX_VERSION = "4.6"
BB_VERSION = "bone3"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV_beaglebone = "${AUTOREV}"
KBRANCH_beaglebone = "linux-4.6.y"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "a"
PR = "${MACHINE_KERNEL_PR}"

SRC_URI[patch.md5sum] = "f6de36b3215daf396ca202113e2cd18b"
SRC_URI[patch.sha256sum] = "ca3041543137709efca353c0640100201b3f1f25620f766e6d5cf11dc68274ba"

