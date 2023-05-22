SUMMARY = "Simple program to read/write from/to any location in memory"

HOMEPAGE = "https://github.com/VCTLabs/devmem2"
SECTION = "libs"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

SRC_URI = "git://github.com/VCTLabs/devmem2.git;branch=master;protocol=https"
SRCREV = "4d684da113648389417d26fde907c3105e1e0a7a"
PV = "0.1+git${SRCPV}"

S = "${WORKDIR}/git"

CFLAGS += "-DFORCE_STRICT_ALIGNMENT"

do_compile() {
    ${CC} -o devmem2 devmem2.c ${CFLAGS} ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install devmem2 ${D}${bindir}
}
