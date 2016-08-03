DESCRIPTION = "CUPS backends, filters, and other software"
HOMEPAGE = "http://www.linuxfoundation.org/collaborate/workgroups/openprinting/cups-filters"

LICENSE = "GPLv2 & LGPLv2 & MIT & GPLv2+ & GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d420e185486344da6176553848350932"

SECTION = "console/utils"

DEPENDS = "cups glib-2.0 dbus dbus-glib lcms ghostscript poppler qpdf libpng"
DEPENDS_class-native = "poppler-native glib-2.0-native dbus-native pkgconfig-native gettext-native libpng-native"

SRC_URI = "http://openprinting.org/download/cups-filters/cups-filters-${PV}.tar.gz"

inherit autotools-brokensep gettext pkgconfig

EXTRA_OECONF_append = " --enable-ghostscript --disable-ijs --disable-ldap \
                       --with-pdftops=hybrid --enable-imagefilters \
                       --enable-ghostscript --with-gs-path=system \
                       --with-fontdir=${datadir}/fonts --with-rcdir=no \
                       --with-cups-rundir=${localstatedir}/run/cups \
                       --localstatedir=${localstatedir}/var \
                       --with-rcdir=no \
                       --without-php"

EXTRA_OECONF_append_class-native = " --with-pdftops=pdftops \
                                    --disable-avahi --disable-ghostscript \
                                    --disable-ijs --disable-ldap \
                                    --with-png --without-jpeg --without-tiff"

EXTRA_OEMAKE = "-j1"

BBCLASSEXTEND = "native"

PACKAGECONFIG[jpeg] = "--with-jpeg,--without-jpeg,jpeg"
PACKAGECONFIG[png] = "--with-png,--without-png,libpng"
PACKAGECONFIG[tiff] = "--with-tiff,--without-tiff,tiff"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'zeroconf', 'avahi', '', d)}"

PACKAGECONFIG[avahi] = "--enable-avahi,--disable-avahi,avahi"
PACKAGECONFIG[dbus] = "--enable-dbus,--disable-dbus,dbus"

PACKAGES += "\
	${PN}-gst \
        ${PN}-data \
        "

FILES_${PN}-gst = "\
	${libdir}/cups/filter/gstopxl \
	${libdir}/cups/filter/gstoraster \
	"

FILES_${PN}-data = "\
	/usr/share/cups/data \
	"

FILES_${PN}-dbg += "\
	${libdir}/cups/filter/.debug \
	${libdir}/cups/backend/.debug \
	"

FILES_${PN} += "\
        ${libdir}/cups/filter/bannertopdf \
        ${libdir}/cups/filter/commandtoescpx \
        ${libdir}/cups/filter/commandtopclx \
        ${libdir}/cups/filter/foomatic-rip \
        ${libdir}/cups/filter/imagetopdf \
        ${libdir}/cups/filter/imagetops \
        ${libdir}/cups/filter/imagetoraster \
        ${libdir}/cups/filter/pdftoijs \
        ${libdir}/cups/filter/pdftoippprinter \
        ${libdir}/cups/filter/pdftoopvp \
        ${libdir}/cups/filter/pdftopdf \
        ${libdir}/cups/filter/pdftops \
        ${libdir}/cups/filter/pdftoraster \
        ${libdir}/cups/filter/pstopdf \
        ${libdir}/cups/filter/rastertoescpx \
        ${libdir}/cups/filter/rastertopclx \
        ${libdir}/cups/filter/textonly \
        ${libdir}/cups/filter/texttopdf \
        ${libdir}/cups/filter/texttops \
        ${libdir}/cups/filter/urftopdf \
        ${libdir}/cups/filter/rastertopdf \
        ${libdir}/cups/filter/sys5ippprinter \
        ${libdir}/cups/backend \
        /usr/share/cups/charsets \
        /usr/share/cups/drv \
        /usr/share/cups/mime \
        /usr/share/cups/ppdc \
        /usr/share/ppd/cupsfilters \
        "

do_install_append() {
	# remove banners, braille, and extra filters
	rm -rf ${D}/usr/share/cups/banners ${D}/usr/share/cups/braille
	rm -f ${D}${libdir}/cups/filter/*brf* ${D}${libdir}/cups/filter/*brl*

	# remove gst, already provided by ghostscript-cups
	#rm -f ${D}${libdir}/cups/filter/gstopxl
	#rm -f ${D}${libdir}/cups/filter/gstoraster

	# remove testprint (already provided by cups)
	rm -f ${D}/usr/share/cups/data/testprint
}

RDEPENDS_${PN} += "bash"
