SECTION = "graphic/utils"
SUMMARY = "giblib is a simple library which wraps imlib2"
HOMEPAGE = "http://www.linuxbrit.co.uk/giblib/"
LICENSE = "BSD-3-Clause"

DEPENDS = "imlib2"

MIRRORS:prepend () {
}

SRC_NAME = "${BPN}-debian-${PV}-13"

SRC_URI = " \
    https://salsa.debian.org/eric/${BPN}/-/archive/debian/${PV}-13/${SRC_NAME}.tar.gz \
    file://refresh-giblib-use-pkgconfig-for-imlib2.patch \
    file://fix-doc-install-path-in-makefile-inputs.patch \
    "

S = "${WORKDIR}/${SRC_NAME}"

inherit autotools-brokensep binconfig pkgconfig

LIC_FILES_CHKSUM = "file://COPYING;md5=dd3cb8d7a69f3d0b2a52a46c92389011"
SRC_URI[sha256sum] = "47374ad248ee233bbfb54f8677c9cf6e6cda63e85ac7e0c89cb362cb72080906"

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

do_install:append () {
    rm -rf $D/user/doc
}

FILES:${PN}-doc = "/usr/share/doc"

