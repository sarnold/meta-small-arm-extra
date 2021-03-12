DESCRIPTION = "embedded router test image (eg, edgerouter or espressobin)"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

IMAGE_FEATURES += "ssh-server-openssh package-management"

inherit core-image extrausers

IMAGE_OVERHEAD_FACTOR = "1.2"
IMAGE_FSTYPES += "wic.gz"

CORE_IMAGE_EXTRA_INSTALL += "\
    kernel-modules \
    ca-certificates \
    libcgroup \
    zram \
    sudo \
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
        /usr/bin/ssh-keygen -b 4048 -t rsa -C "Meta Nas!" -f ${HOME}/.ssh/id_rsa_espressobin
    fi
    mkdir -p ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    cp ${HOME}/.ssh/id_rsa_espressobin.pub  ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chmod 700 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    chmod 600 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chown ${UID_FOR_AUTH}:${UID_FOR_AUTH} -R ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}
}

ROOTFS_POSTPROCESS_COMMAND_espressobin += "set_dtb_link; set_sudoers_rules; set_ssh_keys;"

EXTRA_USERS_PARAMS = "groupadd sudo; useradd -u ${UID_FOR_AUTH} -P '${PASS_FOR_AUTH}' -G sudo ${USER_FOR_AUTH};"
