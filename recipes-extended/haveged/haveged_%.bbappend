FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "file://${BPN}.init"

inherit update-rc.d

INITSCRIPT_NAME = "haveged"
INITSCRIPT_PARAMS = "defaults 05"

INIT_DIR = "${D}${sysconfdir}/init.d"

do_install:append() {
    install -d ${INIT_DIR}
    install -m 0755 ${WORKDIR}/${PN}.init ${INIT_DIR}/${PN}
}

FILES:${PN} += "${sysconfdir}/init.d"
