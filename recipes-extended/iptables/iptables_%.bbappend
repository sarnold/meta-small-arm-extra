FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_espressobin = " \
    file://iptables.rules \
"

do_install_append_espressobin() {
    install -d ${D}${sysconfdir}/iptables
    install -m 0600 ${WORKDIR}/iptables.rules ${D}${sysconfdir}/iptables/iptables.rules
}
