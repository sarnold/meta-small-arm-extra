DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_INSTALL += " kernel-modules"
PACKAGE_INSTALL += " e2fsprogs-e2fsck"
PACKAGE_INSTALL += " e2fsprogs-mke2fs"
PACKAGE_INSTALL += " util-linux-partx"
PACKAGE_INSTALL += " parted"
PACKAGE_INSTALL:remove = " initramfs-live-install initramfs-module-install"
PACKAGE_INSTALL:remove = " initramfs-live-install-efi initramfs-module-install-efi"
PACKAGE_INSTALL:remove = " kernel-image resize-helper"

IMAGE_FEATURES = " \
    ${@bb.utils.contains('DISABLE_ROOT', 'True', '', 'empty-root-password', d)} \
"
