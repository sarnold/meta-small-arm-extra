require recipes-graphics/xorg-driver/xorg-driver-video.inc
SUMMARY = "X.Org X server -- graphics driver for KMS based systems with pluggable GPU backend"
HOMEPAGE = "https://github.com/VCTLabs/xf86-video-armada"

DEPENDS += "virtual/libx11 libdrm-armada xf86driproto etnaviv-headers linux-libc-headers"

LIC_FILES_CHKSUM = "file://COPYING;md5=47f672cfcf14a4b2fc86a0430ced894d"

# keep in sync with libdrm-armada version as n needed
PV = "0.0.1"
PR = "r16"
BRANCH = "devel"

SRC_URI = "git://github.com/VCTLabs/xf86-video-armada;branch=${BRANCH}"

S = "${WORKDIR}/git"
SRCREV = "a5cdb15c7e2552327de4a79be86044d18b4cdad8"

## configure for libdrm-armada DRM etnaviv only
EXTRA_OECONF = " --disable-vivante \
                 --disable-etnaviv \
                 --with-etnaviv-include=${STAGING_DIR_HOST}/usr/include/ \
"

TARGET_CPPFLAGS += "-I${STAGING_DIR_HOST}/usr/include/libdrm \
"
