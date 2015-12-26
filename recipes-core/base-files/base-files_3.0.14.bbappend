FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_raspberrypi = " file://modules "
SRC_URI_append_beaglebone = " file://modules "

conffiles += " ${sysconfdir}/modules"

do_install_append_raspberrypi() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/modules ${D}${sysconfdir}/
}

do_install_append_beaglebone() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/modules ${D}${sysconfdir}/
}

