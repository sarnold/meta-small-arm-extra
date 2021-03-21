# custom oe-based kernel include for arm64multi

DESCRIPTION = "Mainline Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE_espressobin = "espressobin"

KERNEL_FEATURES_remove = "features/debug/printk.scc"
KERNEL_FEATURES_remove = "features/kernel-sample/kernel-sample.scc"
