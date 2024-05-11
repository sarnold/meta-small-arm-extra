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
    lxdm-init \
    \
    openbox \
    openbox-theme-clearlooks \
    openbox-theme-onyx \
    obconf \
    hicolor-icon-theme \
"

# Register lxdm as default DM
set_default_dm () {
    #!/bin/sh -e
    mkdir -p ${IMAGE_ROOTFS}/etc/X11/
    echo "/usr/sbin/lxdm" > ${IMAGE_ROOTFS}/etc/X11/default-display-manager
}

ROOTFS_POSTPROCESS_COMMAND += "set_default_dm;"

