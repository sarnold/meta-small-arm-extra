DESCRIPTION = "embedded router test image (eg, edgerouter or espressobin)"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

IMAGE_FEATURES += "ssh-server-openssh package-management"

inherit core-image

IMAGE_OVERHEAD_FACTOR = "1.2"
IMAGE_FSTYPES += "wic.gz"

CORE_IMAGE_EXTRA_INSTALL += "\
    kernel-modules \
    libcgroup \
    zram \
    nano \
    bash \
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
    iptables \
    openssl \
    procps \
    rng-tools \
    udev-extraconf \
"

set_dtb_link () {
    cd ${IMAGE_ROOTFS}/boot
    ln -snf armada-3720-espressobin.dtb armada-3720-community.dtb
}

ROOTFS_POSTPROCESS_COMMAND_espressobin += "set_dtb_link;"
