DESCRIPTION = "PDF transformation/inspection software"
HOMEPAGE = "http://qpdf.sourceforge.net"
LICENSE = "Artistic-2.0"
SECTION = "libs"
DEPENDS = "libpcre zlib"

SRC_URI = "https://github.com/qpdf/qpdf/archive/release-qpdf-${PV}.tar.gz"

LIC_FILES_CHKSUM = "file://Artistic-2.0;md5=7806296b9fae874361e6fb10072b7ee3"
SRC_URI[md5sum] = "a0601b0bc56d3f412fd3afecfce2721c"
SRC_URI[sha256sum] = "ce323ca692ddc6da31a90ef8a5f7fb7bc6c61c1c037e2eac14e5d0fcfe6a2797"

inherit autotools-brokensep gettext

# disable random file detection for cross-compile
EXTRA_OECONF = "--without-random \
                --disable-static \
                "

EXTRA_OEMAKE_class-target = "LIBTOOL=${STAGING_BINDIR_CROSS}/${HOST_SYS}-libtool"

S="${WORKDIR}/${PN}-release-${PN}-${PV}"

do_configure_prepend() {
	sed -i "/docdir/d" "${S}"/make/libtool.mk
}

DEBIAN_NOAUTONAME_libqpdf = "1"

PACKAGES =+ "libqpdf"
FILES_libqpdf = "${libdir}/libqpdf.so.*"

RDEPEND_${PN} = "libqpdf"
