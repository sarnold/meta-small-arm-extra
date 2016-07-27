SUMMARY = "A set of Python bindings for the libcups library from the CUPS project"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://github.com/Distrotech/pycups/archive/${PV}.tar.gz"

SRC_URI[md5sum] = "0d151fc723e8596761cbc5913e380c2e"
SRC_URI[sha256sum] = "5062106731b41f68e3a65ea1eccdb580ef6d95b11c07b4da64f09501348d238f"

inherit setuptools

RDEPENDS_${PN} = "cups"
