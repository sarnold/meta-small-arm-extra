SUMMARY = "Check system startup after update"
DESCRIPTION = "Runs on post-update first boot to check services and user \
applications, then updates environment vars depending on error state."
SECTION = "admin"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
        file://update-checker.init \
        file://update-checker.conf \
        file://update-checker.sh \
"

inherit systemd update-rc.d

do_install () {
        install -d ${D}${sysconfdir}/init.d ${D}${sysconfdir}/default
        install -m 0755 ${WORKDIR}/update-checker.init ${D}${sysconfdir}/init.d/zzz-update-checker
        install -m 0755 ${WORKDIR}/update-checker.conf ${D}${sysconfdir}/default/update-checker
        # install -d ${D}${systemd_system_unitdir}
        # install -m 0644 ${WORKDIR}/update-checker.service ${D}${systemd_system_unitdir}
        install -d ${D}${sbindir}
        install -m 0755 ${WORKDIR}/update-checker.sh ${D}${sbindir}/update-checker
}

INITSCRIPT_NAME = "zzz-update-checker"
INITSCRIPT_PARAMS = "start 98 3 5 ."

COMPATIBLE_MACHINE = "rpi"
PACKAGE_ARCH = "${MACHINE_ARCH}"

#SYSTEMD_SERVICE:${PN} = "update-checker.service"
FILES:${PN} += "${sysconfdir} ${bindir}"
