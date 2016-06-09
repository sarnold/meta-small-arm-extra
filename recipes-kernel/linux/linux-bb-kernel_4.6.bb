# custom oe-based kernel for upstream plus RCN bb-kernel giant patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-bb.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           http://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://defconfig"

PATCHTOOL = "git"

BRANCH_VERSION = "4.6"
LINUX_VERSION = "${BRANCH_VERSION}.1"
RELEASE_TAG = "v${LINUX_VERSION}"
# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the bone patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.2"
BB_VERSION = "bone3"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV_beaglebone = "${KERNEL_TAG}"
#SRCREV_beaglebone = "${AUTOREV}"
KBRANCH_beaglebone = "linux-4.6.y"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI[patch.md5sum] = "0599dabc8c6538328e678e594556078f"
SRC_URI[patch.sha256sum] = "45c3228bedb2b391cd2ce82849f7c1ef01b0beeed6ec2b0888eb16fe177a275a"
