FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS += " \
    glib-2.0 \
"

EXTRA_OECONF = "PKG_CONFIG_PATH='${STAGING_LIBDIR}/pkgconfig/'"

CACHED_CONFIGUREVARS = " \
    lt_cv_shlibpath_overrides_runpath=yes \
"

do_configure:prepend() {
    cp ${STAGING_DIR_TARGET}/${datadir}/aclocal/gpgme.m4 ${S}/build/
    sed -i -e "s|build/bundled/|build/|" "${S}"/aclocal.m4
}

do_configure:append() {
    find ${B} -name Makefile | xargs sed -i -e "s|usr/lib64|/usr/lib|g"
}


