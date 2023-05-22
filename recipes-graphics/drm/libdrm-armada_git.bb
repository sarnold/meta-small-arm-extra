SUMMARY = "Armada libdrm buffer object management module"

LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://${S}/armada_ioctl.h;beginline=5;endline=7;md5=5f5464f9b3e981ca574e65b00e438561"

SRC_URI[md5sum] = "685502bbb6dbfdf6af53085d560fe3d1"
SRC_URI[sha256sum] = "a6f4e40adda7837221aeb321209b7964983e3d84ea1fc8b34a45508fe834ff9e"

inherit autotools pkgconfig gettext

PV = "0.0.1"
PR = "r16"

SRC_URI = "git://github.com/sarnold/libdrm-armada;branch=master;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "6b461c163b0bd02c76b65d94cc2fb3767167bda8"

DEPENDS = "libdrm linux-libc-headers"

