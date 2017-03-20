SUMMARY = "Python Qt5 Bindings"
AUTHOR = "Phil Thomson @ riverbank.co.uk"
HOMEPAGE = "http://riverbankcomputing.co.uk"
SECTION = "devel/python"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "python3-sip python3-sip-native python3-native qtbase qtsvg qtdeclarative"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/pyqt/PyQt5/PyQt-${PV}/PyQt5_gpl-${PV}.tar.gz \
    file://sessionmanagement-disabled.patch \
"
SRC_URI[md5sum] = "b3171b67c74aa63a3cd2f386660c898b"
SRC_URI[sha256sum] = "be849f212a074049b9ebc10b6c07dddefb86e6d30e8df8a5c715cbb2cf7fad14"


S = "${WORKDIR}/PyQt5_gpl-${PV}"

PARALLEL_MAKE = ""

inherit qmake5 python3native python3-dir distro_features_check
REQUIRED_DISTRO_FEATURES = "x11"

DISABLED_FEATURES = "PyQt_Desktop_OpenGL PyQt_Accessibility PyQt_SessionManager"

DISABLED_FEATURES_append_arm = " PyQt_qreal_double"

PYQT_MODULES = "QtCore QtGui QtNetwork QtSvg QtQml QtQuick QtQuickWidgets QtWidgets"

do_configure() {
    cd ${S}
    echo "py_platform = linux" > pyqt.cfg
    echo "py_inc_dir = %(sysroot)/$includedir/python%(py_major).%(py_minor)m" >> pyqt.cfg
    echo "py_pylib_dir = %(sysroot)/${libdir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "py_pylib_lib = python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> pyqt.cfg
    echo "pyqt_bin_dir = ${D}/${bindir}" >> pyqt.cfg
    echo "pyqt_sip_dir = ${D}/${datadir}/sip/PyQt5" >> pyqt.cfg
    echo "pyuic_interpreter = ${D}/${bindir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_disabled_features = ${DISABLED_FEATURES}" >> pyqt.cfg
    echo "qt_shared = True" >> pyqt.cfg
    echo "[Qt 5.7]" >> pyqt.cfg
    echo "pyqt_modules = ${PYQT_MODULES}" >> pyqt.cfg
    echo yes | python3 configure.py --verbose --qmake  ${STAGING_BINDIR_NATIVE}/qt5/qmake --configuration pyqt.cfg --sysroot ${STAGING_DIR_HOST}
}

do_install() {
    cd ${S}
#    oe_runmake install INSTALL_ROOT=${D}
    oe_runmake install
}

RDEPENDS_${PN} = "python3-core python3-sip"

FILES_${PN} += "${libdir}/${PYTHON_DIR}/site-packages/* ${datadir}/sip/PyQt5/"
FILES_${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/*/.debug/"
