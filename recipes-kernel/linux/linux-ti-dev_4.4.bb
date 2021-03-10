# custom oe-based kernel for upstream plus RCN kernel patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-bb.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=beaglebone \
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
BB_VERSION = "ti-r32"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV_beaglebone = "${KERNEL_TAG}"
#SRCREV_beaglebone = "${AUTOREV}"
KBRANCH_beaglebone = "linux-${BRANCH_VERSION}.y"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

do_install_append() {
	cd ${WORKDIR}
	install -m 0644 -t ${D}/lib/firmware am335x-pru0-fw am335x-pru1-fw
}

RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_beaglebone = " kernel-firmware-am335x-pru"

PACKAGES =+ "kernel-firmware-am335x-pru"
FILES_kernel-firmware-am335x-pru = "/lib/firmware/am335x-pru*"

SRC_URI[patch.md5sum] = "a916db5a77d62177cd97d0e18c92c47e"
SRC_URI[patch.sha256sum] = "2754512e83cfa13c5203351aa9cd7961d11cf822a2bb61c1ccedc8d77c5269e6"

INSANE_SKIP_kernel-firmware-am335x-pru = "arch"
