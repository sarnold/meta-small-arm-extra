SUMMARY = "Allwinner Composite Video Configuration Tool"

HOMEPAGE = "https://projects.nwrk.biz/projects/allwinner-tvout"
SECTION = "console/utils"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=20;endline=32;md5=6f252a421b65bcecf624382ba3c899da"

COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i|sun8i)"

SRC_URI = "git://github.com/VCTLabs/allwinner-tvout.git;branch=master;protocol=https \
           file://allwinner-tvout-1.1-makefile-install.patch \
"

SRCREV = "023f904e5ad357834c606e6ee1b61749a8b52b9e"
PV = "1.1+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "'CC=${CC}' 'CXX=${CXX}'"
TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
    make -C src release
}

do_install () {
    make DESTDIR=${D} PREFIX=/usr -C src install
}
