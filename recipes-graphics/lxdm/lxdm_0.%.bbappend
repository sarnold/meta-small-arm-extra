FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://xserver-lxdm"

inherit update-rc.d

INITSCRIPT_NAME = "xserver-lxdm"
INITSCRIPT_PARAMS_${PN}-init = "defaults 40"

do_install_append_arm() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/xserver-lxdm ${D}${sysconfdir}/init.d/

    sed -i -e '/# session=/a session=/usr/bin/openbox-session' \
        ${D}${sysconfdir}/lxdm/lxdm.conf
}

do_install_append_raspberrypi() {
    sed -i -e '/# bg=/a bg=/usr/share/backgrounds/rpi/fancy-pi.jpg' \
        ${D}${sysconfdir}/lxdm/lxdm.conf
}

PACKAGES =+ "lxdm-init"

RDEPENDS_${PN}-init = " \
    ${VIRTUAL-RUNTIME_xserver_common} xinit lxdm dbus-x11 \
    ${VIRTUAL-RUNTIME_initscripts} \
    ${@base_conditional('ROOTLESS_X', '1', 'xuser-account', '', d)} \
"
RREPLACES_${PN}-init = "xserver-nodm-init"
RCONFLICTS_${PN}-init = "xserver-nodm-init"

FILES_${PN}-init = "${sysconfdir}/init.d"

pkg_postinst_${PN}-init () {
# Register lxdm as default DM
mkdir -p $D${sysconfdir}/X11/
echo "${sbindir}/lxdm" > $D${sysconfdir}/X11/default-display-manager
}

