FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DESCRIPTION = "uEnv.txt for some odd iMX6 boards"
LICENSE = "MIT"

SRC_URI = ""

SRC_URI:append:udooneo = "file://uEnv.txt"
SRC_URI:append:udooqdl = "file://uEnv.txt"
SRC_URI:append:cubox-i = "file://uEnv.txt"

do_install () {
    install -d ${D}/boot
}

do_install:append:udooneo() {
    install -m 0755 ${WORKDIR}/uEnv.txt ${D}/boot
}

do_install:append:udooqdl() {
    install -m 0755 ${WORKDIR}/uEnv.txt ${D}/boot
}

do_install:append:cubox-i() {
    install -m 0755 ${WORKDIR}/uEnv.txt ${D}/boot
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES:${PN} = "/boot"
