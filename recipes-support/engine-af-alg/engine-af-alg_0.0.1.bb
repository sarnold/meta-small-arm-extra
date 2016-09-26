SUMMARY = "af_alg is an openssl crypto engine kernel interface thing"
DESCRIPTION = "\
af_alg is an implementation of a user-space crypto engine for OpenSSL. \
It can be loaded using code, config file or command line and will pass \
openssl crypto functions to the kernel CRYPTO_USER_API (which must be \
enabled in your kernel config).  Especially useful if your device has \
hardware crypto or hash support, but will also use other available kernel \
crypto modules."

HOMEPAGE = "https://github.com/sarnold/af_alg"
SECTION = "Development/Libraries"
LICENSE = "openssl"
LIC_FILES_CHKSUM = "file://COPYING;md5=a9f8132572ec3b99cdb8e0e12afeac58"

DEPENDS = "openssl"

SRC_URI = "git://github.com/sarnold/af_alg.git;protocol=http"
SRCREV = "83fc7bdb76632895cb9ab98ad9c485cb383fbdb5"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "\
    --with-pic \
"

do_configure_prepend () {
    sed -i -e "s|ssl/engines|openssl/engines|" "${S}"/configure.ac

    cd "${S}" && ./autogen.sh
}

FILES_${PN} += "${libdir}/openssl/engines/libaf_alg.so*"
FILES_${PN}-dbg += "${libdir}/openssl/engines/.debug/*"
