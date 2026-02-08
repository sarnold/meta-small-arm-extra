DESCRIPTION = "create swupdate package for core-image-minimal"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit swupdate

SRC_URI = "\
    file://sw-description \
"

# images to build before building swupdate image
IMAGE_DEPENDS = "core-image-minimal"

# images and files that will be included in the .swu image
SWUPDATE_IMAGES = "core-image-minimal"

SWUPDATE_IMAGES_FSTYPES[core-image-minimal] = ".rootfs.ext4.gz"
