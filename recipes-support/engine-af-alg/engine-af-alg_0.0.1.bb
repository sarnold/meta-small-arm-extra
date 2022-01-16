SUMMARY = "af_alg is an openssl crypto engine kernel interface thing"
DESCRIPTION = "\
af_alg is an implementation of a user-space crypto engine plugin for \
OpenSSL. It can be loaded using code, config file or command line and \
will pass openssl crypto functions to the kernel CRYPTO_USER_API (which \
must be enabled in your kernel config).  Especially useful if your device \
has hardware crypto or hash support, but will also use other available kernel \
crypto modules."

HOMEPAGE = "https://github.com/sarnold/af_alg"
SECTION = "Development/Libraries"
LICENSE = "openssl"
LIC_FILES_CHKSUM = "file://COPYING;md5=a9f8132572ec3b99cdb8e0e12afeac58"

DEPENDS = "openssl"

SRC_URI = "http://dev.gentoo.org/~nerdboy/files/af_alg-${PV}.tar.gz"
SRC_URI[md5sum] = "5c9ab96b95fb0f93212121dcb9ba2501"
SRC_URI[sha256sum] = "aeeef47027b06208207553ffc477758d6d4b8334c26b2b233af5127ef7e0a5d9"

S = "${WORKDIR}/af_alg-${PV}"

inherit autotools-brokensep pkgconfig remove-libtool

EXTRA_AUTORECONF = "--make"
REMOVE_LIBTOOL_LA = "1"
EXTRA_OECONF = "\
    --with-pic \
    --enable-static \
    --includedir=${STAGING_INCDIR} \
    --oldincludedir=${STAGING_INCDIR} \
"
CACHED_CONFIGUREVARS = " \
    ac_cv_header_valgrind_valgrind_h=no \
    ac_cv_header_valgrind_memcheck_h=no \
    lt_cv_shlibpath_overrides_runpath=yes \
"

do_configure:prepend() {
    sed -i \
        -e "s|ssl/engines|openssl/engines|" \
        "${S}"/configure.ac
}

do_install:append() {
    install -d ${D}${datadir}/doc/${BPN}/
    install -m 0644 ${S}/README.rst ${D}${datadir}/doc/${BPN}/
}

LIBTOOL = "${STAGING_BINDIR_CROSS}/${HOST_SYS}-libtool"
EXTRA_OEMAKE = "'LIBTOOL=${LIBTOOL}'"
TARGET_CC_ARCH += "${LDFLAGS}"
PARALLEL_MAKE = ""

FILES_${PN} += "${libdir}/openssl/engines/libaf_alg.so"
FILES_${PN}-dbg += "${libdir}/openssl/engines/.debug/*"

PACKAGES = "${PN}-doc ${PN} ${PN}-dbg"
