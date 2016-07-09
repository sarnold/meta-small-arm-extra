FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://lighttpd.ldm.conf"

do_install_append() {
    install -d ${D}${sysconfdir} ${D}/www/pages/dav/upload
    install -m 0644 ${WORKDIR}/lighttpd.ldm.conf ${D}${sysconfdir}/lighttpd.conf
}
