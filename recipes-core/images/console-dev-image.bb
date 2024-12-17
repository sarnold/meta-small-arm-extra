DESCRIPTION = "embedded device console test image"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

IMAGE_FEATURES += " \
    ssh-server-openssh \
    package-management \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    kernel-modules \
    libcgroup \
    zram \
    nano \
    vim \
    vim-vimrc \
    vim-syntax \
    ntp \
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
    python3-redis \
    redis-ipc \
    lighttpd \
    lighttpd-module-cgi \
    lighttpd-module-alias \
    lighttpd-module-status \
    lighttpd-module-setenv \
    lighttpd-module-redirect \
    perl \
    perl-misc \
    perl-modules \
    ${HW_BRINGUP} \
    ${DEV_TOOLS} \
    ${NET_TOOLS} \
    packagegroup-core-full-cmdline \
"

NET_TOOLS = " \
    logrotate \
    arptables \
    ebtables \
    nftables \
    iptables \
    hostapd \
    inetutils \
    iproute2 \
    dnsmasq \
    iputils \
    bridge-utils \
    openvpn \
    bind-utils \
    ufw \
"

HW_BRINGUP = " \
    tzdata \
    iw \
    bonnie++ \
    hdparm \
    iozone3 \
    iperf3 \
    lmbench \
    rt-tests \
    evtest \
    bc \
    memtester \
"

DEV_TOOLS = "\
    diffutils \
    python3-pyserial \
    python3-pyusb \
    python3-pip \
    bison \
    flex \
    yasm \
    gdb \
    gdbserver \
"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

inherit core-image

