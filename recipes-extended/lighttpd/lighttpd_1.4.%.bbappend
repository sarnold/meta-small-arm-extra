FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://${PN}.init \
"

INIT_DIR = "${D}${sysconfdir}/init.d"

do_install_append() {
    install -d ${INIT_DIR}
    install -m 0755 ${WORKDIR}/${PN}.init ${INIT_DIR}/${PN}
}
