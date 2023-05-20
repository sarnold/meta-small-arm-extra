SUMMARY = "A lightweight Terminal Emulator based on libvte, written in Vala"
SECTION = "x11/applications"
DEPENDS = "vte9 vte intltool-native glib-2.0 glib-2.0-native gtk+3 intltool-native"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

PV = "1.3.0+gitr${SRCPV}"
SRCREV = "d281bc63a547bab86e8a92567cb963d1c36d7fb9"
PR = "r2"

inherit autotools pkgconfig perlnative vala

FREESMARTPHONE_GIT ?= "git://github.com/freesmartphone"

SRC_URI = "${FREESMARTPHONE_GIT}/vala-terminal.git;branch=master;protocol=http"
S = "${WORKDIR}/git"
B = "${S}"

RDEPENDS_${PN} += "vte9-termcap ttf-liberation-mono"
RREPLACES_${PN} = "openmoko-terminal2"
RPROVIDES_${PN} = "openmoko-terminal2"
