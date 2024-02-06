FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "file://${BPN}.ldm.conf \
                  file://${BPN}.ldm.init \
"

inherit update-rc.d

INITSCRIPT_NAME = "cupsd"
INITSCRIPT_PARAMS = "defaults 65"

INIT_DIR = "${D}${sysconfdir}/init.d"
CONF_DIR = "${D}${sysconfdir}/default"

DEPENDS += "libgcrypt"

EXTRA_OECONF:append = " --with-docdir=${datadir}/cups/html \
                        --disable-libpaper \
"

do_install:append() {
    install -d ${INIT_DIR} ${CONF_DIR}
    install -m 0644 ${WORKDIR}/${BPN}.ldm.conf ${CONF_DIR}/${PN}
    install -m 0755 ${WORKDIR}/${BPN}.ldm.init ${INIT_DIR}/${PN}d
}

FILES:${PN} += "${datadir}/cups/ \
                ${datadir}/icons/ \
               "