DESCRIPTION = "embedded router test image (eg, edgerouter or espressobin)"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

IMAGE_FEATURES += "ssh-server-openssh package-management"

EXTRA_IMAGE_FEATURES = "debug-tweaks ptest-pkgs tools-sdk tools-debug tools-testapps"

inherit core-image extrausers

IMAGE_OVERHEAD_FACTOR = "1.2"
IMAGE_FSTYPES += "wic.gz"

CORE_IMAGE_EXTRA_INSTALL += "\
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
    sqlite3 \
    python-sqlite3 \
    python-modules \
    python-misc \
    python-pyyaml \
    python-git \
    python-evdev \
    python-redis \
    redis \
    lighttpd \
    lighttpd-module-cgi \
    lighttpd-module-alias \
    lighttpd-module-status \
    lighttpd-module-setenv \
    lighttpd-module-compress \
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
    netmap \
    bridge-utils \
    openvpn \
    bind \
    postfix \
    firewall3 \
    ufw \
    iwinfo \
"

HW_BRINGUP = " \
    tzdata \
    iw \
    bonnie++ \
    hdparm \
    iozone3 \
    iperf \
    lmbench \
    rt-tests \
    evtest \
    bc \
    memtester \
"

DEV_TOOLS = "\
    diffutils \
    python-docutils \
    python-pyserial \
    python-pyusb \
    python-pip \
    guile \
    bison \
    flex \
    yasm \
    gdb \
    gdbserver \
    packagegroup-core-device-devel \
"

USER_FOR_AUTH ?= "admin"
PASS_FOR_AUTH ?= "admin"
UID_FOR_AUTH ?= "1001"

set_dtb_link () {
    cd ${IMAGE_ROOTFS}/boot
    ln -snf armada-3720-espressobin.dtb armada-3720-community.dtb
}

set_sudoers_rules (){
    #!/bin/sh -e
    echo '%sudo ALL=(ALL) ALL' > ${IMAGE_ROOTFS}/etc/sudoers.d/admin
}


set_ssh_keys (){
    #!/bin/sh -e
    if [ ! -s ${HOME}/.ssh/id_rsa_espressobin ]; then
        mkdir -p ${HOME}/.ssh/
        /usr/bin/ssh-keygen -b 4048 -t rsa -C "espressobin admin" -f ${HOME}/.ssh/id_rsa_espressobin
    fi
    mkdir -p ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    cp ${HOME}/.ssh/id_rsa_espressobin.pub  ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chmod 700 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    chmod 600 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chown ${UID_FOR_AUTH}:${UID_FOR_AUTH} -R ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}
}

ROOTFS_POSTPROCESS_COMMAND_edgerouter += "set_sudoers_rules; set_ssh_keys;"
ROOTFS_POSTPROCESS_COMMAND_espressobin += "set_dtb_link; set_sudoers_rules; set_ssh_keys;"

EXTRA_USERS_PARAMS = "groupadd sudo; useradd -u ${UID_FOR_AUTH} -P '${PASS_FOR_AUTH}' -G sudo ${USER_FOR_AUTH};"
