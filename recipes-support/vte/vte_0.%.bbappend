# we want to build and install the vte terminal app
# we also like gnutls for the terminal
PACKAGECONFIG ?= "gnutls vala"

do_compile_append() {
    ninja -v ${PARALLEL_MAKE} all
}

do_install_append() {
    #install -d ${D}${sysconfdir}/profile.d
    #install -m 0755 src/vte.sh ${D}${sysconfdir}/profile.d/
    mv ${D}${bindir}/vte-2.91 ${D}${bindir}/vte-term
}

PACKAGES =+ "vte-term"

RDEPENDS_${PN}-term = "libvte"

FILES_${PN}-term = "${bindir}/vte-term ${sysconfdir}/*"
