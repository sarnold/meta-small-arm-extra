SECTION = "x11/utils"
DESCRIPTION = "GKrellM is a GTK-based stacked monitor program."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6aa4c0c48b808b45244efd507765e2b8"

INC_PR = "r2"

SRC_URI += " \
	file://gkrellmd \
	file://gkrellmd.conf \
"

inherit pkgconfig update-rc.d gettext

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "gkrellmd"
INITSCRIPT_PARAMS_${PN} = "defaults 26"

DEPENDS = "glib-2.0"

EXTRA_OEMAKE = "TARGET= 'CC=${CC}' 'STRIP=' INSTALLROOT='/usr'"

S = "${WORKDIR}/gkrellm-${PV}"

export LINK_FLAGS = "${LDFLAGS}"
export SMC_LIBS = "-lgmodule-2.0 -lm"

do_configure_prepend() {
	sed -i -e "s|getline|gkrellm_getline|" ${S}/src/client.c

	sed -e 's:-O2 ::' \
		-e 's:override CC:CFLAGS:' \
		-e 's:-L/usr/X11R6/lib::' \
		-i */Makefile || die "sed Makefile(s) failed"
}

do_compile() {
	cd server
	oe_runmake STRIP=""
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/gkrellmd ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/gkrellmd.conf ${D}${sysconfdir}

	install -d ${D}${sbindir}
	install -m 0755 server/gkrellmd ${D}${sbindir}/
}

#PROVIDES += "gkrellm-daemon"
RCONFLICTS_${PN} = "gkrellm-daemon"
PACKAGES = "gkrellmd-dbg gkrellmd"

FILES_${PN}-dbg = "${sbindir}/.debug ${prefix}/src"
FILES_${PN} = "${sysconfdir}/gkrellmd.conf ${sbindir} \
  ${sysconfdir}/init.d/gkrellmd \
"

CONFFILES_${PN} = "${sysconfdir}/gkrellmd.conf"

