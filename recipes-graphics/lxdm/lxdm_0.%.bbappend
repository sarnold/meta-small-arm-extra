FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://xserver-lxdm"

inherit update-rc.d

INITSCRIPT_NAME = "xserver-lxdm"
INITSCRIPT_PARAMS:${PN}-init = "defaults 40"

do_install:append:class-target() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/xserver-lxdm ${D}${sysconfdir}/init.d/

    sed -i -e '/# session=/a session=/usr/bin/openbox-session' \
        -e 's/# autologin=dgod/autologin=xuser/' \
        ${D}${sysconfdir}/lxdm/lxdm.conf
}

do_install:append_raspberrypi() {
    sed -i -e '/# bg=/a bg=/usr/share/backgrounds/rpi/fancy-pi.jpg' \
        ${D}${sysconfdir}/lxdm/lxdm.conf
}

PACKAGES =+ "lxdm-init"

RDEPENDS:${PN}-init = " \
    bash \
    xinit lxdm dbus-x11 \
    ${VIRTUAL-RUNTIME_initscripts} \
    ${@oe.utils.conditional('ROOTLESS_X', '1', 'xuser-account', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam-plugin-env', '', d)} \
"
RREPLACES:${PN}-init = "xserver-nodm-init"
RCONFLICTS:${PN}-init = "xserver-nodm-init"

FILES:${PN}-init = "${sysconfdir}/init.d/"
