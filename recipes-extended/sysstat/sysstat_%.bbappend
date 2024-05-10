FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://99_sysstat file://sar.conf file://sar.init file://sar.sh"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d ${D}${sysconfdir}/default/volatiles ${D}${bindir}
    install -m 0644 ${WORKDIR}/99_sysstat ${D}${sysconfdir}/default/volatiles/99_sysstat
    install -m 0644 ${WORKDIR}/sar.conf ${D}${sysconfdir}/default/sar
    install -m 0755 ${WORKDIR}/sar.init ${D}${sysconfdir}/init.d/sar
    install -m 0755 ${WORKDIR}/sar.sh ${D}${bindir}/sar.sh
}

FILES_${PN} += "${bindir}/* ${sysconfdir}/*"
