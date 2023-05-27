DESCRIPTION = "A lightweight panel/taskbar"
SECTION = "x11"
DEPENDS = "cmake-native cairo pango virtual/imlib2 glib-2.0 gtk+3 librsvg \
           virtual/libx11 libxrandr libxcomposite libxinerama libxdamage \
           libxrender libxext startup-notification"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

HOMEPAGE = "https://gitlab.com/nick87720z/tint2"

SRC_URI = "\
    https://gitlab.com/nick87720z/tint2/-/archive/${PV}/${BPN}-${PV}.tar.bz2 \
"

SRC_URI[md5sum] = "6fc5731e7425125fa84a2add5cef4bff"
SRC_URI[sha256sum] = "fe106e6a6057d2631abddde9f82d3fd4fb1985c4fb93f10d3886417a9e22471d"

PACKAGECONFIG ??= "startup-notification"
PACKAGECONFIG[startup-notification] = "-DENABLE_SN=ON,-DENABLE_SN=OFF,startup-notification"

inherit setuptools3 cmake gobject-introspection gettext features_check mime-xdg
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data x11"

EXTRA_OECMAKE = " \
    -DENABLE_BATTERY=OFF \
    -DENABLE_TINT2CONF=ON \
"

FILES:${PN} += "${sysconfdir} /usr/share/*"
CONFFILES:${PN} = "${sysconfdir}/xdg/tint2/tint2rc"

