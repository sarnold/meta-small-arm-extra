SUMMARY = "Minimalistic C client library for the Redis database"

HOMEPAGE = "https://github.com/redis/hiredis"
SECTION = "Development/Libraries"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=d84d659a35c666d23233e54503aaea51"

DEPENDS = "redis"

SRC_URI = "https://github.com/redis/${PN}/archive/v${PV}.tar.gz \
           file://hiredis-0.13.1-use-install-command-instead-of-cp.patch \
"
SRC_URI[md5sum] = "4226dda52dbe08304e3b6027b4d6c74d"
SRC_URI[sha256sum] = "8865105e15331156a74b64aafbfd3f8c784a8375e003a55512dcca3d82168487"

CPPFLAGS_APPEND = " -D_GNU_SOURCE"
EXTRA_OEMAKE = "AR='${AR}' CC='${CC}' ARCH= DEBUG= OPTIMIZATION='${CPPFLAGS}'"

do_compile() {
    oe_runmake dynamic static hiredis.pc
}

do_install() {
    export PREFIX=${D}/${prefix}
    oe_runmake install

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${S}/${PN}.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${datadir}/doc/${BPN}/
    install -m 0644 ${S}/README.md ${D}${datadir}/doc/${BPN}/
}

