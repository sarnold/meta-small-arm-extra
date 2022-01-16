FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "file://${BPN}.init \
"

INIT_DIR = "${D}${sysconfdir}/init.d"

do_install:append() {
    install -d ${INIT_DIR}
    install -m 0755 ${WORKDIR}/${PN}.init ${INIT_DIR}/${PN}
}
