XSERVER = " \
    localedef \
    libx11-locale \
    libxi \
    libxtst \
    xserver-xf86-config \
    xorg-minimal-fonts \
    xkeyboard-config \
    setxkbmap \
    xkbcomp \
    xmodmap \
    xrdb \
    rgb \
    xprop \
    xterm \
"

WINDOW_MANAGER = " \
    openbox \
    openbox-config \
    openbox-core \
    openbox-theme-bear2 \
    openbox-theme-clearlooks \
    openbox-theme-onyx \
    libcanberra-gtk2 \
    gdk-pixbuf-loader-ico \
    gdk-pixbuf-loader-bmp \
    gdk-pixbuf-loader-ani \
    gdk-pixbuf-xlib \
    gnome-icon-theme \
    hicolor-icon-theme \
    xcursor-transparent-theme \
    pyxdg \
    xdg-utils \
    obconf \
    tint2 \
    lxdm-init \
    x11vnc \
"

IMAGE_INSTALL += "\
    ${XSERVER} \
    ${WINDOW_MANAGER} \
"

