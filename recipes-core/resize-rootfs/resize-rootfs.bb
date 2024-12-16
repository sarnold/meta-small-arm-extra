DESCRIPTION = "Resize Rootfs systemd service"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

SRC_URI = "file://resize-rootfs.init"

inherit update-rc.d

INITSCRIPT_NAME = "resize-rootfs"
INITSCRIPT_PARAMS = "start 10 S ."

RDEPENDS:${PN} = "e2fsprogs-resize2fs parted	"

do_install () {
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/resize-rootfs.init ${D}${sysconfdir}/init.d/${PN}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
