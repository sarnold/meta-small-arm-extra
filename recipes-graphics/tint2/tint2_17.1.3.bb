DESCRIPTION = "A lightweight panel/taskbar"
SECTION = "x11"
DEPENDS = "cmake-native cairo pango virtual/imlib2 glib-2.0 gtk+3 librsvg \
           virtual/libx11 libxrandr libxcomposite libxinerama libxdamage \
           libxrender libxext startup-notification"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

HOMEPAGE = "https://gitlab.com/nick87720z/tint2"

SRCREV = "57e96cee7c98c0f272befd34fd959964052e51c4"

SRC_URI = "\
    git://gitlab.com/nick87720z/tint2;protocol=https;branch=master \
"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "startup-notification"
PACKAGECONFIG[startup-notification] = "-DENABLE_SN=ON,-DENABLE_SN=OFF,startup-notification"

inherit setuptools3 cmake pkgconfig gobject-introspection gettext features_check mime mime-xdg
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data x11"

EXTRA_OECMAKE = " \
    -DENABLE_BATTERY=OFF \
    -DENABLE_TINT2CONF=ON \
"

FILES:${PN} += "${sysconfdir} /usr/share/*"
CONFFILES:${PN} = "${sysconfdir}/xdg/tint2/tint2rc"


