SUMMARY = "QT5 Qtwebkit Kiosk"
DESCRIPTION = "This is an example web kiosk using Qt5 and Qtwebkit"
HOMEPAGE = "https://github.com/VCTLabs/qt-webkit-kiosk"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://doc/lgpl.html;md5=8bc8c892efca25740ff051514126b2f6"

inherit qmake5 qmake5_paths

DEPENDS = "qtbase qtdeclarative qtgraphicaleffects qtmultimedia qtwebkit sqlite3"

SRCREV = "7915a93b2fdd8d15d2bab85f2717b903cf2cc509"
SRC_URI = "git://github.com/VCTLabs/qt-webkit-kiosk;branch=master;protocol=https"

S = "${WORKDIR}/git"

EXTRA_QMAKEVARS_PRE = "\
    PREFIX=${OE_QMAKE_PATH_PREFIX} \
    LIBDIR=${OE_QMAKE_PATH_LIBS} \
    DATADIR=${OE_QMAKE_PATH_DATA} \
    QT_INSTALL_PLUGINS=${OE_QMAKE_PATH_PLUGINS} \
"

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

do_compile:prepend() {
    export PKG_CONFIG_PATH=$(qmake -query QT_INSTALL_LIBS)/pkconfig
}

FILES:${PN}-dbg += "${datadir}/${P}/.debug"
FILES:${PN} += "${datadir}"

RDEPENDS:${PN} = "libx11-xcb qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"
