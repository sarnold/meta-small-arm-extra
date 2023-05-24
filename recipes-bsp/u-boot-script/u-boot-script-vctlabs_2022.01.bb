FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DESCRIPTION = "Useful boot scripts for router images"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "file://boot.cmd"

S = "${WORKDIR}"

inherit deploy

BOOTSCRIPT = "${S}/boot.cmd"
FILES:${PN} = "/boot"

do_mkimage () {
    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot script" -d ${BOOTSCRIPT} ${S}/boot.scr
}

addtask mkimage after do_compile before do_install

do_deploy () {
    install -d ${DEPLOYDIR}
    install ${S}/boot.scr ${DEPLOYDIR}/boot.scr

    cd ${DEPLOYDIR}
    mv boot.scr boot.scr-${MACHINE}-${PV}-${PR}
    ln -sf boot.scr-${MACHINE}-${PV}-${PR} boot.scr-${MACHINE}
    ln -sf boot.scr-${MACHINE}-${PV}-${PR} boot.scr

}

do_install () {
    install -d ${D}/boot
    install ${S}/boot.scr ${D}/boot
}

addtask deploy after do_install before do_build

do_compile[noexec] = "1"

RPROVIDES:${PN} += "u-boot-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(espressobin|espressobin-v7|espressobin-ultra)"
