SUMMARY = "embedded LXDE development image"

LICENSE = "MIT"

inherit core-image features_check
require console-dev-image.bb

REQUIRED_DISTRO_FEATURES = "x11"
CONFLICT_DISTRO_FEATURES = "wayland"

IMAGE_FEATURES += "splash"

IMAGE_LINGUAS = " "

IMAGE_INSTALL += " \
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
