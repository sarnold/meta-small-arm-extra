# custom oe-based kernel include for rcn armv7multi
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

SUMMARY = "Linux kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

INC_PR = "r1"

DEPENDS += "xz-native bc-native"
RDEPENDS_kernel-base += "kernel-devicetree"

KERNEL_DEVICETREE_nitrogen6x = "imx6q-sabrelite.dtb \
                  imx6q-nitrogen6x.dtb imx6dl-nitrogen6x.dtb \
                  imx6q-nitrogen6_max.dtb imx6qp-nitrogen6_max.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE_nitrogen6x = "nitrogen6x"

S = "${WORKDIR}/git"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "x"
PR = "${MACHINE_KERNEL_PR}"

do_configure_append() {
	echo "CONFIG_LOCALVERSION="\"${LINUX_VERSION_EXTENSION}\" >> ${B}/.config
}
