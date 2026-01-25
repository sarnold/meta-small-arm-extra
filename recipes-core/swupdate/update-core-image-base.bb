DESCRIPTION = "Generating the update image for SWUpdate"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Add all local files to be added to the SWU
# sw-description must always be in the list.
# You can extend with scripts or whatever you need
SRC_URI = " \
    file://sw-description \
    "

# images to build before building update image
IMAGE_DEPENDS = "core-image-base"

# images and files that will be included in the .swu image
SWUPDATE_IMAGES = "core-image-base"

# a deployable image can have multiple format, choose one
SWUPDATE_IMAGES_FSTYPES[core-image-base] = ".rootfs.ext4.gz"
SWUPDATE_IMAGES_FSTYPES[uImage] = ".bin"

inherit swupdate
