FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append_espressobin = " \
    file://iptables.rules \
"

do_install:append_espressobin() {
    install -d ${D}${sysconfdir}/iptables
    install -m 0600 ${WORKDIR}/iptables.rules ${D}${sysconfdir}/iptables/iptables.rules
}
