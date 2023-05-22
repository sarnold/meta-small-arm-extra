##require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for Boundary Devices boards."
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
COMPATIBLE_MACHINE = "(imx6qsabrelite|nitrogen6x|nitrogen6x-lite|nitrogen6sx|nitrogen7|imx6-acl)"

PROVIDES += "u-boot virtual/bootloader"
DEPENDS = "u-boot-mkimage-native"

PV = "v2017.01-nitro"

SRCREV = "7d3bb1b0b5ca4416e3bf4a00645a09025c8469ff"
BRANCH = "v2017.01-nitro"
SRC_URI = "git://github.com/VCTLabs/u-boot.git;branch=${BRANCH};protocol=https"

S = "${WORKDIR}/git"

UBOOT_ENV_BINARY = "uEnv.txt"
UBOOT_ENV_IMAGE = "uEnv-${MACHINE}-${PV}-${PR}.txt"
UBOOT_ENV_SYMLINK = "uEnv-${MACHINE}.txt"

inherit deploy

BOOTSCRIPT = "${S}/board/boundary/nitrogen6x/6x_bootscript_uEnv.txt"
UPGRADESCRIPT = "${S}/board/boundary/nitrogen6x/6x_upgrade.txt"

# u-boot blows up on hardened but only the nitrogen configs
# we also need to overide do_install since we only need scripts
do_compile[noexec] = "1"

do_mkimage () {
    # allow deploy to use the ${MACHINE} name to simplify things
    if [ ! -d ${S}/board/boundary/${MACHINE} ]; then
        mkdir ${S}/board/boundary/${MACHINE}
    fi

    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot script" -d ${BOOTSCRIPT} \
                  ${S}/board/boundary/${MACHINE}/6x_bootscript

    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "upgrade script" -d ${UPGRADESCRIPT} \
                  ${S}/board/boundary/${MACHINE}/6x_upgrade
}

addtask mkimage after do_compile before do_install

do_install() {
    install -d ${D}/boot
    install ${S}/board/boundary/${MACHINE}/${UBOOT_ENV_BINARY} ${D}/boot/${UBOOT_ENV_IMAGE}
    ln -sf ${UBOOT_ENV_IMAGE} ${D}/boot/${UBOOT_ENV_BINARY}

    install ${S}/board/boundary/${MACHINE}/6x_bootscript \
            ${D}/boot/6x_bootscript-${MACHINE}-${PV}-${PR}
    install ${S}/board/boundary/${MACHINE}/6x_upgrade \
            ${D}/boot/6x_upgrade-${MACHINE}-${PV}-${PR}

    rm -f ${D}/boot/6x_bootscript-${MACHINE} ${D}/boot/6x_upgrade-${MACHINE}
    ln -sf 6x_bootscript-${MACHINE}-${PV}-${PR} ${D}/boot/6x_bootscript-${MACHINE}
    ln -sf 6x_upgrade-${MACHINE}-${PV}-${PR} ${D}/boot/6x_upgrade-${MACHINE}
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install ${S}/board/boundary/${MACHINE}/6x_bootscript \
            ${DEPLOYDIR}/6x_bootscript-${MACHINE}-${PV}-${PR}
    install ${S}/board/boundary/${MACHINE}/6x_upgrade \
            ${DEPLOYDIR}/6x_upgrade-${MACHINE}-${PV}-${PR}
    install ${S}/board/boundary/${MACHINE}/${UBOOT_ENV_BINARY} \
            ${DEPLOYDIR}/${UBOOT_ENV_IMAGE}

    rm -f ${DEPLOYDIR}/${UBOOT_ENV_BINARY} ${DEPLOYDIR}/${UBOOT_ENV_SYMLINK}
    ln -sf ${UBOOT_ENV_IMAGE} ${DEPLOYDIR}/${UBOOT_ENV_BINARY}
    ln -sf ${UBOOT_ENV_IMAGE} ${DEPLOYDIR}/${UBOOT_ENV_SYMLINK}

    cd ${DEPLOYDIR}
    rm -f 6x_bootscript-${MACHINE} 6x_upgrade-${MACHINE}
    ln -sf 6x_bootscript-${MACHINE}-${PV}-${PR} 6x_bootscript-${MACHINE}
    ln -sf 6x_upgrade-${MACHINE}-${PV}-${PR} 6x_upgrade-${MACHINE}
}

addtask deploy after do_install before do_package

FILES:${PN} = "/boot/*"

PACKAGE_ARCH = "${MACHINE_ARCH}"

