FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://${BPN}.ldm.conf \
                  file://${BPN}.ldm.init \
"

inherit update-rc.d

INITSCRIPT_NAME = "cupsd"
INITSCRIPT_PARAMS = "defaults 65"

INIT_DIR = "${D}${sysconfdir}/init.d"
CONF_DIR = "${D}${sysconfdir}/default"

DEPENDS += "libgcrypt"

EXTRA_OECONF_append = " --with-docdir=${datadir}/cups/html \
                        --disable-libpaper \
"

do_install_append() {
    install -d ${INIT_DIR} ${CONF_DIR}
    install -m 0644 ${WORKDIR}/${PN}.ldm.conf ${CONF_DIR}/${PN}
    install -m 0755 ${WORKDIR}/${PN}.ldm.init ${INIT_DIR}/${PN}d
}

FILES_${PN} += "${datadir}/cups/ \
                ${datadir}/icons/ \
               "
