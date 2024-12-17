DESCRIPTION = "embedded router test image (eg, edgerouter or espressobin)"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

IMAGE_FEATURES:append = " \
    ssh-server-openssh \
    package-management \
"

CORE_IMAGE_EXTRA_INSTALL:append = " \
    kernel-modules \
    ca-certificates \
    resize-rootfs \
    libcgroup \
    zram \
    nano \
    screen \
    mtd-utils \
    devmem2 \
    sysfsutils \
    spitools \
    i2c-tools \
    usbutils \
    distro-feed-configs \
    cpufrequtils \
    gkrellmd \
    dnsmasq \
    haveged \
    udev-extraconf \
    packagegroup-core-full-cmdline \
"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

inherit core-image
require scan-user.inc

IMAGE_OVERHEAD_FACTOR = "1.2"
IMAGE_FSTYPES:append = " wic.gz"

set_dtb_link () {
    cd ${IMAGE_ROOTFS}/boot
    if [ -f armada-3720-espressobin.dtb ]; then
        ln -snf armada-3720-espressobin.dtb armada-3720-community.dtb
    fi
}

ROOTFS_POSTPROCESS_COMMAND:espressobin += "set_dtb_link;"
