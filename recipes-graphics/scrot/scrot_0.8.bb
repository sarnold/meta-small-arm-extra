SECTION = "graphic/utils"
SUMMARY = "scrot (SCReen shOT), screen capture utility"
DESCRIPTION = "scrot (SCReen shOT) is a simple commandline screen capture utility that uses imlib2 to grab and save images"
HOMEPAGE = "http://linuxbrit.co.uk/software/"
LICENSE = "BSD"

RDEPENDS_${PN} = "imlib2-loaders"
DEPENDS = "imlib2 giblib"

MIRRORS_prepend () {
}

SRC_URI = " \
    http://pkgs.fedoraproject.org/repo/pkgs/scrot/scrot-${PV}.tar.gz/ccae904d225609571bdd3b03445c1e88/scrot-${PV}.tar.gz \
    file://fix-scrot-include-paths.patch \
"
LIC_FILES_CHKSUM = "file://COPYING;md5=dd3cb8d7a69f3d0b2a52a46c92389011"
SRC_URI[md5sum] = "ccae904d225609571bdd3b03445c1e88"
SRC_URI[sha256sum] = "613d1cf524c2b62ce3c65f1232ea4f05c7daf248d5e82ff2a6892c98093994f2"

PR = "r0"

FILES_${PN}-doc += "/usr/doc"

inherit autotools

