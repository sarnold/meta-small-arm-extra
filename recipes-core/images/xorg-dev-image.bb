DESCRIPTION = "Full-ish dev image with X11, test, and sample apps."

require dev-user.inc

export IMAGE_BASENAME = "xorg-dev-image"

IMAGE_FEATURES += "splash x11 x11-base hwcodecs"

inherit features_check

REQUIRED_DISTRO_FEATURES = "x11 opengl"

CORE_IMAGE_BASE_INSTALL += "\
    sudo \
    libcap \
    libcap-bin \
    kernel-modules \
    packagegroup-core-x11 \
"

X_PACKAGES = "\
    feh \
    scrot \
    openbox \
    obconf \
    hicolor-icon-theme \
    tint2 \
    gmrun \
    libxi \
    xdg-utils \
    xdg-user-dirs \
    xterm \
    pyxdg \
    vte-term \
    geany \
    geany-plugins \
    mesa-demos \
"

FONT_PACKAGES = "\
    ttf-dejavu-common \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
"

# if we have locales, we should set a default by installing
# default-locale

CORE_IMAGE_EXTRA_INSTALL += "\
    distro-feed-configs \
    gdb \
    ldd \
    curl \
    git \
    gzip \
    less \
    nano \
    wget \
    strace \
    tar \
    bzip2 \
    zip \
    unzip \
    vim \
    \
    ${FONT_PACKAGES} \
    ${X_PACKAGES} \
"

inherit core-image
