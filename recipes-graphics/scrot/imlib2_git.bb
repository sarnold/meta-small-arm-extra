SUMMARY = "A graphic library for file loading, saving, rendering, and manipulation"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=344895f253c32f38e182dcaf30fe8a35"

DEPENDS = "freetype libpng jpeg virtual/libx11 libxext librsvg"
PROVIDES = "virtual/imlib2"
PV = "1.11.1+gitr${SRCPV}"
SRCREV = "5d73c5364043c9b11e9d6e9c417de887d9aee9b1"

inherit autotools binconfig pkgconfig
SRC_URI = "git://git.enlightenment.org/old/legacy-${BPN}.git;branch=master"
S = "${WORKDIR}/git"

# autotools-brokensep
B = "${S}"

PACKAGECONFIG ??= "gif bzip2"
PACKAGECONFIG[gif] = "--with-gif,--without-gif,giflib"
PACKAGECONFIG[tiff] = "--with-tiff,--without-tiff,tiff"
PACKAGECONFIG[bzip2] = "--with-bz2,--without-bz2,bzip2"
PACKAGECONFIG[id3] = "--with-id3,--without-id3,libid3tag"

EXTRA_OECONF = "--with-svg \
                --with-x \
                --x-includes=${STAGING_INCDIR} \
                --x-libraries=${STAGING_LIBDIR} "

# TODO: Use more fine granular version
#OE_LT_RPATH_ALLOW=":${libdir}/imlib2/loaders:${libdir}/imlib2/filters:"
OE_LT_RPATH_ALLOW = "any"
OE_LT_RPATH_ALLOW[export]="1"

PACKAGES =+ "imlib2-loaders-dbg imlib2-filters-dbg imlib2-loaders imlib2-filters ${PN}-bin imlib2-themes "
FILES:${PN} = "${libdir}/lib*.so.* ${libdir}/imlib2/*/*.so"
FILES:${PN}-dbg = "${libdir}/.debug/ ${bindir}/.debug/ ${prefix}/src/debug/"
FILES:${PN}-dev += "${bindir}/imlib2-config ${libdir}/*.so ${includedir}"
FILES:${PN}-bin = "${bindir}"
FILES:imlib2-themes = "${datadir}/imlib2/data"
FILES:imlib2-loaders = "${libdir}/imlib2/loaders/*.so"
FILES:imlib2-filters = "${libdir}/imlib2/filters/*.so"
FILES:imlib2-loaders-dbg += "${libdir}/imlib2/loaders/.debug"
FILES:imlib2-filters-dbg += "${libdir}/imlib2/filters/.debug"

# png.so jpeg.so id3.so are also provided by lightmediascanner
PRIVATE_LIBS:imlib2-loaders = "\
    png.so jpeg.so svg.so pnm.so lbm.so argb.so zlib.so bmp.so tga.so gif.so xpm.so bz2.so \
"

PRIVATE_LIBS:imlib2-filters = "bumpmap.so colormod.so testfilter.so"

do_configure:prepend() {
    autopoint || touch config.rpath
}

do_install:prepend () {
    for i in `find ${B}/ -name "*.pc" -type f` ; do \
        sed -i -e 's:-L${STAGING_LIBDIR}:-L\$\{libdir\}:g' -e 's:-I${STAGING_LIBDIR}:-I\$\{libdir\}:g' -e 's:-I${STAGING_INCDIR}:-I\$\{includedir\}:g' $i
    done
}

do_install:append() {
    rm -rf ${D}/${datadir}
}
