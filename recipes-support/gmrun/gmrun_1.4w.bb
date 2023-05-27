SUMMARY = "A GTK based launcher box with bash style auto completion"
HOMEPAGE = "https://github.com/wdlkmpx/gmrun"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=004ee725f3667c7b91ff19b0b9181635"

DEPENDS = "gtk+3"

SRC_URI = "\
    https://github.com/wdlkmpx/gmrun/releases/download/${PV}/${BPN}-${PV}.tar.xz \
"
SRC_URI[sha256sum] = "eaeb68c4ef9da383b0966420fdf1515eb86654673463dfc70124c880dbadf800"

inherit bash-completion pkgconfig mime-xdg

EXTRA_OECONF = "\
    --prefix=${prefix} \
    --sysconfdir=${sysconfdir} \
    --with-sysroot=${STAGING_DIR_HOST} \
    --disable-gtk2 \
    --enable-xdg \
"

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('USE_NLS', 'yes', 'nls', '', d)} \
"
PACKAGECONFIG[nls] = "--enable-nls,---disable-nls"

EXTRA_OEMAKE += 'libdir=${prefix}/lib LDLIBS="${LDLIBS}"'

CFLAGS:append = " -fPIC"

do_configure() {
    ./configure ${EXTRA_OECONF}
}

do_install() {
    oe_runmake install DESTDIR=${D}
}
