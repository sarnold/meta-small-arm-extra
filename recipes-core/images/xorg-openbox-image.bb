DESCRIPTION = "A custom openbox image based on console-image-plus.bb"
PR = "r3"

# base image features set in console-image-plus.bb
IMAGE_FEATURES += "x11 x11-base hwcodecs"

inherit core-image features_check

REQUIRED_DISTRO_FEATURES = "x11"

# set the following parameters here
DEFAULT_TIMEZONE = "PST8PDT"

# for real-time audio support (includes RT_GROUP_SCHED)
KERNEL_ENABLE_CGROUPS = "1"

# add some X and custom desktop apps
include xorg-openbox.inc
include desktop-apps.inc

export IMAGE_BASENAME = "xorg-openbox-image"

CORE_IMAGE_EXTRA_INSTALL += "\
    ${XSERVER} \
    ${WINDOW_MANAGER} \
"
