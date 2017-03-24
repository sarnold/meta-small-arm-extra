FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += " \
    glib-2.0 \
"

EXTRA_OECONF = "PKG_CONFIG_PATH='${STAGING_LIBDIR}/pkgconfig/'"

CACHED_CONFIGUREVARS = " \
    lt_cv_shlibpath_overrides_runpath=yes \
"

do_configure_append() {
    find ${B} -name Makefile | xargs sed -i -e "s|usr/lib64|/usr/lib|g"
}


