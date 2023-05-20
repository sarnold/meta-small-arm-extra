FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://xserver-lxdm"

inherit update-rc.d

INITSCRIPT_NAME = "xserver-lxdm"
INITSCRIPT_PARAMS_${PN}-init = "defaults 40"

do_install_append_class-target() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/xserver-lxdm ${D}${sysconfdir}/init.d/

    sed -i -e '/# session=/a session=/usr/bin/openbox-session' \
        -e 's/# autologin=dgod/autologin=xuser/' \
        ${D}${sysconfdir}/lxdm/lxdm.conf
}

do_install:append:raspberrypi() {
    sed -i -e '/# bg=/a bg=/usr/share/backgrounds/rpi/fancy-pi.jpg' \
        ${D}${sysconfdir}/lxdm/lxdm.conf
}

PACKAGES =+ "lxdm-init"

RDEPENDS_${PN}-init = " \
    bash \
    ${VIRTUAL-RUNTIME_xserver_common} xinit lxdm lxrandr dbus-x11 \
    ${VIRTUAL-RUNTIME_initscripts} \
    ${@oe.utils.conditional('ROOTLESS_X', '1', 'xuser-account', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam-plugin-env', '', d)} \
"
RREPLACES_${PN}-init = "xserver-nodm-init"
RCONFLICTS_${PN}-init = "xserver-nodm-init"

FILES_${PN}-init = "${sysconfdir}/init.d"

# Register lxdm as default DM
pkg_postinst_ontarget_${PN}-init () {
    mkdir -p ${sysconfdir}/X11/
    echo "${sbindir}/lxdm" > ${sysconfdir}/X11/default-display-manager
}
