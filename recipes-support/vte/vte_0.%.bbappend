# we want to build and install the vte terminal app
# we also like gnutls for the terminal
PACKAGECONFIG ?= "gnutls"

do_compile:append() {
    ninja -v ${PARALLEL_MAKE} all
}

do_install:append() {
    #install -d ${D}${sysconfdir}/profile.d
    #install -m 0755 src/vte.sh ${D}${sysconfdir}/profile.d/
    mv ${D}${bindir}/vte-2.91 ${D}${bindir}/vte-term
}

PACKAGES =+ "vte-term"

RDEPENDS:${PN}-term = "libvte"

FILES:${PN}-term = "${bindir}/vte-term ${sysconfdir}/*"
