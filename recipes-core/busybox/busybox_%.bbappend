FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://nice.cfg file://swclock"

PACKAGES += "${PN}-swclock"

FILES_${PN}-swclock = "${sysconfdir}/init.d/swclock"

INITSCRIPT_PACKAGES += "${PN}-swclock"

INITSCRIPT_NAME_${PN}-swclock = "swclock"
INITSCRIPT_PARAMS_${PN}-swclock = "start 02 S ."

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/swclock ${D}${sysconfdir}/init.d/
}
