FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://openbox-gnome-session-3.4.9.patch \
            file://mini_x.session \
            file://menu.xml \
"

#SRC_URI_append_rpi = "http://www.gentoogeek.org/files/rpi-backgrounds.tar.gz;name=backgrounds"

EXTRA_OECONF += "--disable-rpath"

#do_configure_prepend() {
#    autopoint || touch config.rpath
#    autoreconf -Wcross --verbose --install --force || bbnote "failed to autoreconf"
#}

do_install_append_class-target() {
    # normally installs as /etc/mini_x/session 
    install -d ${D}${sysconfdir}/mini_x
    install -T -m 0755 ${WORKDIR}/mini_x.session ${D}${sysconfdir}/mini_x/session

    # add default menu
    cp -f ${WORKDIR}/menu.xml ${D}/${sysconfdir}/xdg/openbox/
}

#do_install_append_rpi() {
#    # add some rpi images (creative commons share-able)
#    install -d ${D}/usr/share/backgrounds/rpi
#    install ${S}/rpi-backgrounds/* ${D}/usr/share/backgrounds/rpi/
#}

#PACKAGES_prepend_rpi = "openbox-backgrounds "

FILES_${PN}-gnome += "${datadir}/gnome/ ${datadir}/gnome-session"
#FILES_${PN}-backgrounds = "/usr/share/backgrounds/*"
FILES_${PN}-config += "${sysconfdir}/mini_x/*"

SRC_URI[source.md5sum] = "1ccc090eb34d85a91e83feb994b6eaf9"
SRC_URI[source.sha256sum] = "59f5f0d626a74141921432eec9131759b5991b63d904f6dfbaef2bb5061f0a3f"

#SRC_URI[backgrounds.md5sum] = "ab88c26e62df7e5bbb088318a5407149"
#SRC_URI[backgrounds.sha256sum] = "f119970a604060a1f7972065e1b2080d1a1db80c2ac5ed33cefc1a061c3f1ce2"
