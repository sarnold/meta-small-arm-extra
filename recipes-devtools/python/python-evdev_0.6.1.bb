SUMMARY = "Python evdev lib"
HOMEPAGE = "https://github.com/gvalkov/python-evdev"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18debddbb3f52c661a129724a883a8e2"

SRC_URI = "https://github.com/gvalkov/python-evdev/archive/v${PV}.tar.gz \
           file://input-event-codes.patch \
           "
SRC_URI[md5sum] = "0c16da7f8e590430a831c37315df27c4"
SRC_URI[sha256sum] = "02a77b56596d9a34342eb5f384c736d1e2df0299ebed6d9d4fe46549b9262df0"

inherit distutils
