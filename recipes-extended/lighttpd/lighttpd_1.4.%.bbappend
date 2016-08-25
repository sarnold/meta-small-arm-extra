FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://${PN}-1.4.39-include-glob.patch \
                  file://${PN}.ldm.conf \
                  file://${PN}.ldm.init \
                  file://ldm.conf \
"

INIT_DIR = "${D}${sysconfdir}/init.d"
CONF_DIR = "${D}${sysconfdir}"
INC_DIR = "${D}${sysconfdir}/lighttpd.d"
WEB_DIR = "${D}/www/pages/dav/upload"

do_install_append() {
    install -d ${INIT_DIR} ${INC_DIR} ${WEB_DIR}
    install -m 0644 ${WORKDIR}/${PN}.ldm.conf ${CONF_DIR}/${PN}.conf
    install -m 0644 ${WORKDIR}/ldm.conf ${CONF_DIR}/
    install -m 0755 ${WORKDIR}/${PN}.ldm.init ${INIT_DIR}/${PN}
}
