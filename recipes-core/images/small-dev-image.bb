DESCRIPTION = "small device dev image (eg, beaglebone, fruity-pi)"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

IMAGE_FEATURES:append = " \
    ssh-server-openssh \
    package-management \
"

EXTRA_IMAGE_FEATURES ?= "debug-tweaks"

require devel-small.inc

IMAGE_INSTALL:append = " ${CORE_IMAGE_EXTRA_INSTALL} resize-helper"

IMAGE_LINGUAS = " "

inherit core-image

IMAGE_OVERHEAD_FACTOR = "1.2"
IMAGE_FSTYPES:append = " cpio.gz wic.xz"
