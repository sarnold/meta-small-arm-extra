DESCRIPTION = "initramfs devel image"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require devel-initramfs.inc
# alternative to debug-tweaks or empty-root-password
#require initramfs-image-harden.inc

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = " \
    ${@bb.utils.contains('DISABLE_ROOT', 'True', '', 'empty-root-password', d)} \
"

export IMAGE_BASENAME = "devel-initramfs"
IMAGE_NAME_SUFFIX ?= ""

# don't actually generate an image, just the artifacts needed for one
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

# BASE_DEVEL is the default from the include file
PACKAGE_INSTALL = " \
    initscripts \
    sysvinit \
    init-ifupdown \
    ${BASE_DEVEL_INSTALL} \
"

# Don't allow the initramfs to contain a kernel
PACKAGE_EXCLUDE = "kernel-image-*"

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
BAD_RECOMMENDATIONS += "busybox-syslog"

PACKAGE_ARCH = "${MACHINE_ARCH}"
