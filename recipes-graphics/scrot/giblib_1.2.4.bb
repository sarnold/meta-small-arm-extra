SECTION = "graphic/utils"
SUMMARY = "giblib is a simple library which wraps imlib2"
HOMEPAGE = "http://www.linuxbrit.co.uk/giblib/"
LICENSE = "BSD-3-Clause"

DEPENDS = "imlib2"

MIRRORS:prepend () {
}

SRC_URI = " \
    https://salsa.debian.org/eric/${BPN}/-/archive/debian/${PV}-13/${BPN}-debian-${PV}-13.tar.gz \
    file://giblib-fix-build-system.patch \
    file://giblib-use-pkgconfig-for-imlib2.patch \
"
LIC_FILES_CHKSUM = "file://COPYING;md5=dd3cb8d7a69f3d0b2a52a46c92389011"
SRC_URI[sha256sum] = "6ac9ebba9097168f2507e71eae926d23218b5c947d09020a24d20526c5ff7753"

PR = "r13"

do_compile:prepend () {
    export DESTDIR=${D}
}

do_compile:append () {
    for i in $(find ${B} -name "*?.pc") ; do
        sed -i -e s:${STAGING_DIR_TARGET}::g \
               -e s:/${TARGET_SYS}::g \
                  $i
    done
}

FILES:${PN}-doc = "/usr/share/doc"

inherit autotools-brokensep binconfig pkgconfig

