FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append_raspberrypi = " file://modules "
SRC_URI:append_beaglebone = " file://modules "

conffiles += " ${sysconfdir}/modules"

do_install:append_raspberrypi() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/modules ${D}${sysconfdir}/
}

do_install:append_beaglebone() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/modules ${D}${sysconfdir}/
}

