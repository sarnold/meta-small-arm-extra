DESCRIPTION = "The stand-alone daemon portion of GKrellm desktop monitor."
HOMEPAGE="http://www.gkrellm.net/"
SECTION = "net/misc"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6aa4c0c48b808b45244efd507765e2b8"

SRC_URI = "http://gkrellm.srcbox.net/gkrellm-${PV}.tar.bz2 \
           file://gkrellm-2.3.5-config.patch \
           file://gkrellm-2.3.5-sansfont.patch \
           file://gkrellm-2.3.5-width.patch \
"

require gkrellmd.inc

PR = "${INC_PR}.1"

SRC_URI[md5sum] = "de25d51653567a896979bcce8c91a019"
SRC_URI[sha256sum] = "1ee0643ed9ed99f88c1504c89d9ccb20780cf29319c904b68e80a8e7c8678c06"
