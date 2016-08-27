# custom yocto-based kernel for upstream plus RCN bb-kernel giant patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc
require uenv-fw-hdrs.inc

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=beaglebone \
           http://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://defconfig"

BRANCH_VERSION = "4.6"
LINUX_VERSION = "${BRANCH_VERSION}.2"
RELEASE_TAG = "v${LINUX_VERSION}"

# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the bone patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.2"
BB_VERSION = "bone3"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

RDEPENDS_kernel-base += "kernel-devicetree"
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb am335x-bonegreen.dtb"
RDEPENDS_kernel-base_append_beaglebone = " kernel-firmware-am335x-pm"

SRCREV_beaglebone = "${KERNEL_TAG}"
#SRCREV_beaglebone = "${AUTOREV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

KBRANCH_beaglebone = "linux-4.6.y"

COMPATIBLE_MACHINE_beaglebone = "beaglebone"

S = "${WORKDIR}/git"

do_configure_append() {
	echo "CONFIG_LOCALVERSION="\"${LINUX_VERSION_EXTENSION}\" >> ${B}/.conf$
}

SRC_URI[patch.md5sum] = "08140a75d99ab9680ad4a604f2ab864e"
SRC_URI[patch.sha256sum] = "18f58d19853ab30493c3f394a1db119f28e5043910854273226305f4a79d7200"

