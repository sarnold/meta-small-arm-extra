# custom oe-based kernel include for bone kernels

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

SUMMARY = "Linux kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

INC_PR = "r1"

DEPENDS += "xz-native bc-native"

RDEPENDS_kernel-base += "kernel-devicetree"
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb am335x-bonegreen.dtb"
RDEPENDS_kernel-base_append_beaglebone = " kernel-firmware-am335x-pm"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE_beaglebone = "beaglebone"

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"

do_install_append() {
	cd ${S}/firmware
	install -d ${D}/lib/firmware
	install -m 0644 -t ${D}/lib/firmware \
		am335x-pm-firmware.bin am335x-pm-firmware.elf am335x-bone-scale-data.bin
}

PACKAGES =+ "kernel-firmware-am335x-pm"
FILES_kernel-firmware-am335x-pm = "/lib/firmware/am335x-pm-firmware.bin /lib/firmware/am335x-pm-firmware.elf /lib/firmware/am335x-bone-scale-data.bin"


