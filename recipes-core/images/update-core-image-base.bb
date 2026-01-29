DESCRIPTION = "create swupdate package for core-image-base"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit swupdate

SRC_URI = "\
    file://preinstall.sh \
    file://postinstall.sh \
    file://sw-description \
"

# images to build before building swupdate image
IMAGE_DEPENDS = "core-image-base"

# images and files that will be included in the .swu image
SWUPDATE_IMAGES = "core-image-base"

SWUPDATE_IMAGES_FSTYPES[core-image-base] = ".rootfs.ext4.gz"
