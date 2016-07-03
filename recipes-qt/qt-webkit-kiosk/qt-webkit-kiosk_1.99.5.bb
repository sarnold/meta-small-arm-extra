SUMMARY = "QT5 Qtwebkit Kiosk"
DESCRIPTION = "This is an example web kiosk using Qt5 and Qtwebkit"
HOMEPAGE = "https://github.com/VCTLabs/qt-webkit-kiosk"
LICENSE = "LGPL-3"
LIC_FILES_CHKSUM = "file://doc/lgpl.html;md5=8bc8c892efca25740ff051514126b2f6"

inherit qmake5

DEPENDS = "qtbase qtdeclarative qtgraphicaleffects qtwebkit sqlite3 pulseaudio"

SRCREV = "7915a93b2fdd8d15d2bab85f2717b903cf2cc509"
SRC_URI = "git://github.com/VCTLabs/qt-webkit-kiosk;branch=next;protocol=https"

S = "${WORKDIR}/git"

do_compile_prepend() {
    export PKG_CONFIG_PATH=$(qmake -query QT_INSTALL_LIBS)/pkconfig
}

do_install() {
    install -d ${D}${datadir}/${P}
    #install -m 0755 ${B}/QUItBattery ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/* ${D}${datadir}/${P}
}

FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"

#RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"
