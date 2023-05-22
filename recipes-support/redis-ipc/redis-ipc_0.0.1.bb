SUMMARY = "A convenience library for using redis server and JSON as IPC mechanism"

HOMEPAGE = "https://github.com/VCTLabs/redis-ipc"
SECTION = "Development/Libraries"
LICENSE = "OpenSSL"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

DEPENDS = "hiredis json-c"

SRC_URI = "https://github.com/VCTLabs/redis-ipc/archive/v${PV}.tar.gz \
           file://redis-ipc-0.0.1-fix-qa-warning.patch \
           file://redis-ipc-0.0.1-autotools-updates.patch \
"
SRC_URI[md5sum] = "475b42836e32de9cc5a485eae0d9c2d0"
SRC_URI[sha256sum] = "28cb0fc70441698f2e9c23697adb80e719e77e614241c71b7a95b532a49fc3d2"

inherit autotools pkgconfig remove-libtool

EXTRA_AUTORECONF = "--make"
REMOVE_LIBTOOL_LA = "1"
EXTRA_OECONF = "\
    --host=${HOST_SYS} \
    --build=${BUILD_SYS} \
    --target=${TARGET_SYS} \
    --with-libtool-sysroot=${STAGING_DIR_HOST} \
    --enable-static \
"

CACHED_CONFIGUREVARS = " \
    lt_cv_shlibpath_overrides_runpath=yes \
"

do_configure:append() {
    sed -i -e "s|I\$(includedir)|I${STAGING_INCDIR}|" \
        "${B}"/src/Makefile
}

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dbg = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
DEBIAN_NOAUTONAME:${PN}-doc = "1"
DEBIAN_NOAUTONAME:${PN}-staticdev = "1"

RRECOMMENDS:${PN} = "redis"

do_install:append() {
    install -d ${D}${datadir}/doc/${BPN}/ 
    install -m 0644 ${S}/README.rst ${D}${datadir}/doc/${BPN}/
}

LIBTOOL = "${STAGING_BINDIR_CROSS}/${HOST_SYS}-libtool"
EXTRA_OEMAKE = "'LIBTOOL=${LIBTOOL}'"

TARGET_CC_ARCH += "${LDFLAGS}"
PARALLEL_MAKE = ""
