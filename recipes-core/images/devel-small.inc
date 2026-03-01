# include file for devel images

IMAGE_INSTALL = "packagegroup-core-boot"

IMAGE_INSTALL:append = "\
    kernel-modules \
    libcgroup \
    zram \
    nano \
    ntp \
    rsync \
    bash \
    screen \
    pax-utils \
    mtd-utils \
    devmem2 \
    sysfsutils \
    spitools \
    i2c-tools \
    usbutils \
    distro-feed-configs \
    cpufrequtils \
    haveged \
"
# ${@bb.utils.contains('INIT_MANAGER', 'systemd', '', 'zram', d)}
