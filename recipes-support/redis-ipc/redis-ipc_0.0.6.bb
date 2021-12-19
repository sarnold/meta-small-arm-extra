SUMMARY = "A convenience library for using redis server and JSON as IPC mechanism"

HOMEPAGE = "https://github.com/VCTLabs/redis-ipc"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "hiredis json-c"

SRC_URI = "git://github.com/VCTLabs/redis-ipc.git;branch=develop;protocol=https"
SRCREV = "c65233bb5fa27d0b2db1bd12bda42e04cd382257"
PV = "v0.0.6+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
    -DBUILD_STATIC_LIBS=ON \
    -DRIPC_BUILD_TESTING=OFF \
    -DCMAKE_CXX_IMPLICIT_INCLUDE_DIRECTORIES:PATH='${STAGING_INCDIR}' \
"

inherit cmake pkgconfig

DEBIAN_NOAUTONAME_${PN} = "1"
DEBIAN_NOAUTONAME_${PN}-dbg = "1"
DEBIAN_NOAUTONAME_${PN}-dev = "1"
DEBIAN_NOAUTONAME_${PN}-doc = "1"
DEBIAN_NOAUTONAME_${PN}-staticdev = "1"

RRECOMMENDS_${PN} = "redis"

do_install_append() {
    install -d ${D}${datadir}/doc/${BPN}/
    install -m 0644 ${S}/README.rst ${D}${datadir}/doc/${BPN}/
}
