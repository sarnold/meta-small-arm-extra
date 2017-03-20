DESCRIPTION = "openbox configuration program"
AUTHOR = "openbox.org"
HOMEPAGE_URL="http://openbox.org/wiki/ObConf:About"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://openbox.org/dist/obconf/${P}.tar.gz"

DEPENDS = "openbox gtk+ libglade virtual/gettext libxml2"
RDEPENDS_${PN} = "openbox gtk+"

#S = "${WORKDIR}"

inherit autotools pkgconfig gettext

export BUILD_SYS
export HOST_SYS

EXTRA_OECONF = "PKG_CONFIG_PATH='${STAGING_LIBDIR}/pkgconfig/'"

FILES_${PN} += "/usr/share/*"

SRC_URI[md5sum] = "9271c5d2dc366d61f73665a5e8bceabc"
SRC_URI[sha256sum] = "71a3e5f4ee246a27421ba85044f09d449f8de22680944ece9c471cd46a9356b9"
