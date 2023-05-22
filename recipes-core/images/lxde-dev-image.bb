SUMMARY = "embedded LXDE development image"

LICENSE = "MIT"

inherit core-image features_check
require console-dev-image.bb

REQUIRED_DISTRO_FEATURES = "ipv4 x11 polkit initscripts sysvinit"
CONFLICT_DISTRO_FEATURES = "wayland"

IMAGE_FEATURES += "splash"

IMAGE_LINGUAS = " "

IMAGE_INSTALL += " \
    packagegroup-core-x11 \
    packagegroup-lxde-extended \
    kernel-modules \
    \
    lxdm \
    \
    openbox \
    openbox-theme-clearlooks \
    openbox-theme-onyx \
    obconf \
    hicolor-icon-theme \
"
