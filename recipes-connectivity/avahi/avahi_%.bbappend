FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_espressobin = " \
    file://avahi-daemon.conf \
"

do_install_append_espressobin() {
    install -d ${D}${sysconfdir}/avahi
    install -m 0644 ${WORKDIR}/avahi-daemon.conf ${D}${sysconfdir}/avahi/avahi-daemon.conf
}
