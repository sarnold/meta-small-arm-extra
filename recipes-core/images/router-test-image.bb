DESCRIPTION = "espressobin router test image"
LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-openssh package-management"

inherit core-image

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

ROOTFS_POSTPROCESS_COMMAND += "set_dtb_link;"
