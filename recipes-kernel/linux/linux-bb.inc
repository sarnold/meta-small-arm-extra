# custom oe-based kernel include for bone kernels
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

SUMMARY = "Linux kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

SRC_URI_append = " file://uEnv.txt  file://uEnv.txt.root"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

INC_PR = "r2"

DEPENDS += "xz-native bc-native"

RDEPENDS_kernel-base += "kernel-devicetree kernel-uenv"
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb am335x-bonegreen.dtb"
RDEPENDS_kernel-base_append_beaglebone = " kernel-firmware-am335x-pm kernel-firmware-am335x-pru"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE_beaglebone = "beaglebone"

S = "${WORKDIR}/git"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "a"
PR = "${MACHINE_KERNEL_PR}"

do_configure_append() {
	echo "CONFIG_LOCALVERSION="\"${LINUX_VERSION_EXTENSION}\" >> ${B}/.config
}

do_install_append() {
	oe_runmake headers_install INSTALL_HDR_PATH=${D}${exec_prefix}/src/linux-${KERNEL_VERSION} ARCH=$ARCH

	echo "uname_r=${LINUX_VERSION}${LINUX_VERSION_EXTENSION}" >> ${WORKDIR}/uEnv.txt

	install -d ${D}/boot/
        install -m 0644 ${WORKDIR}/uEnv.txt ${D}/boot/uEnv.txt
	install -m 0644 ${WORKDIR}/uEnv.txt.root ${D}/uEnv.txt

	cd ${S}/firmware
	install -d ${D}/lib/firmware
	install -m 0644 -t ${D}/lib/firmware \
		am335x-pm-firmware.bin am335x-pm-firmware.elf am335x-bone-scale-data.bin

	rm -f ${D}${KERNEL_SRC_PATH}/arch/*/vdso/vdso*.so
}

do_deploy_append() {
	install -m 0644 -t ${DEPLOYDIR} ${WORKDIR}/uEnv.txt ${WORKDIR}/uEnv.txt.root
}

PACKAGES =+ "kernel-firmware-am335x-pm kernel-uenv kernel-headers"
FILES_kernel-uenv = "/boot/uEnv.txt /uEnv.txt"
FILES_kernel-headers = "${exec_prefix}/src/linux*"
FILES_kernel-firmware-am335x-pm = "/lib/firmware/am335x-pm-firmware.bin /lib/firmware/am335x-pm-firmware.elf /lib/firmware/am335x-bone-scale-data.bin"

