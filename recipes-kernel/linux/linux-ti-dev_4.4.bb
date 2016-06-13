# custom oe-based kernel for upstream plus RCN kernel patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-bb.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           http://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://am335x-pru0-fw \
           file://am335x-pru1-fw \
           file://defconfig"

PATCHTOOL = "git"

BRANCH_VERSION = "4.4"
LINUX_VERSION = "${BRANCH_VERSION}.12"
RELEASE_TAG = "v${LINUX_VERSION}"
# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.12"
BB_VERSION = "ti-r30"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV_beaglebone = "${KERNEL_TAG}"
#SRCREV_beaglebone = "${AUTOREV}"
KBRANCH_beaglebone = "linux-${BRANCH_VERSION}.y"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

do_install_append() {
	cd ${WORKDIR}
	install -m 0644 -t ${D}/lib/firmware am335x-pru0-fw am335x-pru1-fw
}

PACKAGES =+ "kernel-firmware-am335x-pru"
FILES_kernel-firmware-am335x-pru = "/lib/firmware/am335x-pru*"

SRC_URI[patch.md5sum] = "a69602f341afe922ef3bf7ad79489206"
SRC_URI[patch.sha256sum] = "5eecc3eaf2455e4d866c592ee8b75bbe8d190e719d83095fce9ebbd7ad93d0d4"

INSANE_SKIP_kernel-firmware-am335x-pru = "arch"
