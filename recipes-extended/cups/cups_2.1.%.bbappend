FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://${PN}.ldm.conf \
                  file://${PN}.ldm.init \
"

inherit update-rc.d

INITSCRIPT_NAME = "cupsd"
INITSCRIPT_PARAMS = "defaults 65"

INIT_DIR = "${D}${sysconfdir}/init.d"
CONF_DIR = "${D}${sysconfdir}/default"

do_install_append() {
    install -d ${INIT_DIR} ${CONF_DIR}
    install -m 0644 ${WORKDIR}/${PN}.ldm.conf ${CONF_DIR}/${PN}
    install -m 0755 ${WORKDIR}/${PN}.ldm.init ${INIT_DIR}/${PN}d
}

RDEPENDS_${PN} = "bash"
