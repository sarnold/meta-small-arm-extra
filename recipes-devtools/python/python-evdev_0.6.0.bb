SUMMARY = "Python evdev lib"
HOMEPAGE = "https://github.com/gvalkov/python-evdev"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18debddbb3f52c661a129724a883a8e2"

SRC_URI = "https://github.com/gvalkov/python-evdev/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz \
           file://use-sysroot-headers.patch \
"

SRC_URI[md5sum] = "963ea4bc961434ee355bfea6b697a4e3"
SRC_URI[sha256sum] = "2a65a1c2657a7d9b989bb4557ddd27fd4be85c46e26f74362fe5c46939d4c857"

DEPENDS_${PN} += "\
    ${PYTHON_PN}-ctypes \
"

inherit setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-fcntl \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-stringold \
"

do_install_append() {
    # note the full docs require Sphinx to build them
    install -d ${D}${datadir}/doc/${BPN}/
    install -m 0644 ${S}/README.rst ${D}${datadir}/doc/${PN}/
}

PACKAGES = "${PN}-doc ${PN} ${PN}-dbg"
