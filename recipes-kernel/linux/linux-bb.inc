# custom oe-based kernel include for bone kernels
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit kernel
require uenv-fw-hdrs.inc

DESCRIPTION = "Mainline Linux Kernel with extra beaglebone stuff"
SECTION = "kernel"
LICENSE = "GPLv2"

SRC_URI:append = " file://uEnv.txt  file://uEnv.txt.root"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

INC_PR = "r2"

DEPENDS += "xz-native bc-native"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb am335x-bonegreen.dtb"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE_beaglebone = "beaglebone"

S = "${WORKDIR}/git"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "a"
PR = "${MACHINE_KERNEL_PR}"

do_configure:append() {
	echo "CONFIG_LOCALVERSION="\"${LINUX_VERSION_EXTENSION}\" >> ${B}/.config
}
