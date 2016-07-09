FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://lighttpd.ldm.conf \
                  file://lighttpd.ldm.init \
"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d ${D}/www/pages/dav/upload
    install -m 0644 ${WORKDIR}/lighttpd.ldm.conf ${D}${sysconfdir}/lighttpd.conf
    install -m 0755 ${WORKDIR}/lighttpd.ldm.init ${D}${sysconfdir}/init.d/lighttpd
}
