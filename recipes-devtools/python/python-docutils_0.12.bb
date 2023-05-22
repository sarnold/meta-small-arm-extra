SUMMARY = "Text processing system for documentation"
HOMEPAGE = "http://docutils.sourceforge.net"
SECTION = "devel/python"
LICENSE = "PSF-2.0 & BSD-2-Clause & GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=a722fbdc20347db7b69223594dd54574"

DEPENDS = "python"

SRC_URI = "${SOURCEFORGE_MIRROR}/docutils/docutils-${PV}.tar.gz"
SRC_URI[md5sum] = "4622263b62c5c771c03502afa3157768"
SRC_URI[sha256sum] = "c7db717810ab6965f66c8cf0398a98c9d8df982da39b4cd7f162911eb89596fa"

S = "${WORKDIR}/docutils-${PV}"

inherit distutils3

BBCLASSEXTEND = "native"

