DESCRIPTION = "The stand-alone daemon portion of GKrellm desktop monitor."
HOMEPAGE="http://www.gkrellm.net/"
SECTION = "net/misc"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6aa4c0c48b808b45244efd507765e2b8"

SRC_URI = "http://gkrellm.srcbox.net/gkrellm-${PV}.tar.bz2 \
           file://gkrellm-2.3.5-cifs.patch \
           file://gkrellm-2.3.5-config.patch \
           file://gkrellm-2.3.5-sansfont.patch \
           file://gkrellm-2.3.5-width.patch \
           file://gkrellm-2.3.7-pkgconfig_fix_newlines.patch \
"

require gkrellmd.inc

PR = "${INC_PR}.1"

SRC_URI[md5sum] = "88e3ec4f1d98ffc674447db533db6713"
SRC_URI[sha256sum] = "f7a4642d2cc6b61242215e58e1bd8fe394e6fd984cd3c3f8964c1c554029735d"

