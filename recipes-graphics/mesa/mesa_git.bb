require ${BPN}.inc

WANT_LLVM_RELEASE = "3.3"
GALLIUMDRIVERS_mx6 = "etnaviv,imx"
GALLIUMDRIVERS_raspberrypi = "vc4"
EGL_PLATFORMS = "drm,x11,wayland"

DEFAULT_PREFERENCE = "1"

## branch 17.0
SRCREV = "8fee1d348cc3d91a88319c0d72689acabaa2bf47"
PV = "17.0.1+git${SRCPV}"

SRC_URI = "git://anongit.freedesktop.org/git/mesa/mesa;branch=17.0 \
           file://mesa-17-clover-Work-around-build-failure-with-AltiVec.patch \
"

S = "${WORKDIR}/git"

DEPENDS += "python-mako-native"

do_configure() {
    cd ${S}
    autoreconf -Wcross --verbose --install --force --exclude=autopoint ${EXTRA_AUTORECONF} $acpaths || die "autoreconf execution failed."

    cd ${B}
    ${S}/configure ${CONFIGUREOPTS} ${EXTRA_OECONF}

    find ${B} -name Makefile | xargs sed -i -e "s|/usr/bin/wayland-scanner|${STAGING_BINDIR_NATIVE}/wayland-scanner|"
}

inherit pythonnative

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#if defined(MESA_EGL_NO_X11_HEADERS)$/#if defined(MESA_EGL_NO_X11_HEADERS) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)}/' ${D}${includedir}/EGL/eglplatform.h
    fi
}

