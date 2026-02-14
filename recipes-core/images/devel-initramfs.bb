DESCRIPTION = "initramfs devel image"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require devel-initramfs.inc

export IMAGE_BASENAME = "devel-initramfs"

inherit core-image

# BASE_DEVEL is the default from the include file
PACKAGE_INSTALL = " \
    initscripts \
    sysvinit \
    ${BASE_DEVEL_INSTALL} \
"

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
BAD_RECOMMENDATIONS += "busybox-syslog busybox-udhcpc"
