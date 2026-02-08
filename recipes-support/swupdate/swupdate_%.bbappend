FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

PACKAGECONFIG_CONFARGS = ""

SRC_URI += " \
    file://09-swupdate-args \
    file://swupdate.cfg \
    ${@bb.utils.contains('INIT_MANAGER','systemd','file://systemd.cfg','',d)} \
    "

RDEPENDS:${PN} += "u-boot-fw-utils"

UNPACKDIR = "${WORKDIR}"

do_configure:prepend() {
    # fix root home directory in sysv init script
    sed -i -e "s|/home||" ${WORKDIR}/swupdate
}

do_install:append() {
    install -d ${D}${sysconfdir}/swupdate/conf.d/
    install -m 0644 ${WORKDIR}/09-swupdate-args ${D}${sysconfdir}/swupdate/conf.d/
    sed -i "s|@MACHINE@|${MACHINE}|g" ${D}${sysconfdir}/swupdate/conf.d/09-swupdate-args

    install -d ${D}${sysconfdir}
    install -m 644 ${WORKDIR}/swupdate.cfg ${D}${sysconfdir}

    if ${@bb.utils.contains('INIT_MANAGER','systemd','false','true',d)}; then
        # fix root home directory in sysv init script
        sed -i -e "s|/home/root/|${ROOT_HOME}/|" ${D}${sysconfdir}/init.d/swupdate
    fi
}
