DESCRIPTION = "The stand-alone daemon portion of GKrellm desktop monitor."
HOMEPAGE="http://www.gkrellm.net/"
SECTION = "net/misc"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=6aa4c0c48b808b45244efd507765e2b8"

SRC_URI = "http://gkrellm.srcbox.net/gkrellm-${PV}.tar.bz2 \
           file://gkrellm-2.3.5-config.patch \
           file://gkrellm-2.3.5-sansfont.patch \
           file://gkrellm-2.3.5-width.patch \
"

require gkrellmd.inc

PR = "${INC_PR}.1"

SRC_URI[md5sum] = "ccc0b6af434542a2374e34a135ae68da"
SRC_URI[sha256sum] = "8b9ec8baadcd5830c6aff04ba86dc9ed317a15c1c3787440bd1e680fb2fcd766"

