SUMMARY = "LXDE GUI interface to RandR extention"
HOMEPAGE = "http://lxde.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/lxde/${BPN}-${PV}.tar.xz \
"
SRC_URI[md5sum] = "b327938f18a4baac85c4707f927d606e"
SRC_URI[sha256sum] = "6d98338485a90d9e47f6d08184df77ca0d9715517f8a45a914e861750589184e"

PE = "1"

DEPENDS = "virtual/libintl intltool-native gtk+ virtual/libx11 libxrandr randrproto"

inherit autotools pkgconfig gettext
# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"

RDEPENDS_${PN} += "xrandr"
