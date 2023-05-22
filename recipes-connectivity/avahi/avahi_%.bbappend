FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append_espressobin = " \
    file://avahi-daemon.conf \
"

do_install:append_espressobin() {
    install -d ${D}${sysconfdir}/avahi
    install -m 0644 ${WORKDIR}/avahi-daemon.conf ${D}${sysconfdir}/avahi/avahi-daemon.conf
}
