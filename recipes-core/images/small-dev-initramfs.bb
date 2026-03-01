DESCRIPTION = "small device initramfs replacement for legacy ramdisk"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# try to avoid dependency loops
EXTRA_IMAGEDEPENDS = ""

require devel-small.inc

IMAGE_FEATURES = "${@bb.utils.contains('EXTRA_IMAGE_FEATURES', 'debug-tweaks', 'empty-root-password', '', d)}"

IMAGE_INSTALL:append = " dropbear ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

inherit image

export IMAGE_BASENAME = "small-dev-initramfs"
IMAGE_NAME_SUFFIX ?= ""

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
IMAGE_FSTYPES:remove = " wic wic.* wic.xz wic.bmap tar.xz"

IMAGE_OVERHEAD_FACTOR = "1.0"

# Don't allow the initramfs to contain a kernel
PACKAGE_EXCLUDE = "kernel-image-*"
IMAGE_INSTALL:remove = "resize-helper"

IMAGE_ROOTFS_SIZE = "16384"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
BAD_RECOMMENDATIONS += "busybox-syslog"

ROOTFS_POSTPROCESS_COMMAND += "remove_file_cruft;"

remove_file_cruft() {
    # boot dir should be empty in ramdisk root
    rm -rf ${IMAGE_ROOTFS}/boot/*
}
