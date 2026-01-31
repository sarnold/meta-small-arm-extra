# include file for devel images

IMAGE_INSTALL:append = "\
    ${@bb.utils.contains('INIT_MANAGER', 'systemd', '', ' packagegroup-core-boot', d)} \
    kernel-modules \
    libcgroup \
    zram \
    nano \
    vim \
    vim-vimrc \
    vim-syntax \
    ntp \
    ltp \
    git \
    rsync \
    bash \
    screen \
    pax-utils \
    pps-tools \
    mtd-utils \
    devmem2 \
    sysfsutils \
    spitools \
    i2c-tools \
    usbutils \
    distro-feed-configs \
    cpufrequtils \
    gkrellmd \
    haveged \
    sqlite3 \
    python3-modules \
    python3-misc \
    python3-pyyaml \
    python3-git \
    python3-evdev \
    perl \
    perl-misc \
    perl-modules \
    ${@bb.utils.contains('INIT_MANAGER', 'systemd', ' swupdate-usb', '', d)} \
"
