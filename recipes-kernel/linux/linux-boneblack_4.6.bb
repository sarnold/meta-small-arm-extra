# custom yocto-based kernel for upstream plus RCN bb-kernel giant patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${LINUX_VERSION}:"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           http://rcn-ee.net/deb/xenial-armhf/v${LINUX_VERSION}.0-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://defconfig"

LINUX_VERSION = "4.6"
BB_VERSION = "bone3"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

RDEPENDS_kernel-base += "kernel-devicetree"
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb am335x-bonegreen.dtb"
RDEPENDS_kernel-base_append_beaglebone = " kernel-firmware-am335x-pm"

SRCREV_beaglebone = "${AUTOREV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

KBRANCH_beaglebone = "linux-4.6.y"

COMPATIBLE_MACHINE_beaglebone = "beaglebone"

S = "${WORKDIR}/git"

do_install_append() {
	cd ${S}/firmware
	install -d ${D}/lib/firmware
	install -m 0644 -t ${D}/lib/firmware \
		am335x-pm-firmware.bin am335x-pm-firmware.elf am335x-bone-scale-data.bin
}

PACKAGES =+ "kernel-firmware-am335x-pm"
FILES_kernel-firmware-am335x-pm = "/lib/firmware/am335x-pm-firmware.bin /lib/firmware/am335x-pm-firmware.elf /lib/firmware/am335x-bone-scale-data.bin"

SRC_URI[patch.md5sum] = "f6de36b3215daf396ca202113e2cd18b"
SRC_URI[patch.sha256sum] = "ca3041543137709efca353c0640100201b3f1f25620f766e6d5cf11dc68274ba"

