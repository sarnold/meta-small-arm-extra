FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DESCRIPTION = "uEnv.txt for UDOO boards and cubox-i"
LICENSE = "GPLv2+"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
"

#SRC_URI = "file://readme.txt"
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
FILES_${PN} = "/boot"
