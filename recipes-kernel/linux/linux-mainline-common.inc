# custom oe-based kernel include for arm64multi
#FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DESCRIPTION = "Mainline Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

#KCONFIG_MODE="--alldefconfig"

KBUILD_DEFCONFIG_espressobin = "defconfig"

COMPATIBLE_MACHINE = "(espressobin)"
KERNEL_DEVICETREE_espressobin = "marvell/armada-3720-espressobin.dtb"

KERNEL_FEATURES_remove = "features/debug/printk.scc"
KERNEL_FEATURES_remove = "features/kernel-sample/kernel-sample.scc"

