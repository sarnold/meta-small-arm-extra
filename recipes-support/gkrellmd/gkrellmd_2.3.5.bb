DESCRIPTION = "The stand-alone daemon portion of GKrellm desktop monitor."
SECTION = "net/misc"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6aa4c0c48b808b45244efd507765e2b8"

SRC_URI = "http://gkrellm.srcbox.net/gkrellm-${PV}.tar.bz2 \
           file://gkrellm-2.3.5-autofs.patch \
           file://gkrellm-2.3.5-binding.patch \
           file://gkrellm-2.3.5-cifs.patch \
           file://gkrellm-2.3.5-config.patch \
           file://gkrellm-2.3.5-dso.patch \
           file://gkrellm-2.3.5-format-security.patch \
           file://gkrellm-2.3.5-sansfont.patch \
           file://gkrellm-2.3.5-width.patch \
           file://gkrellm-2.3.5-fix-ipv6-cidr-match.patch \
"

require gkrellmd.inc

PR = "${INC_PR}.1"

SRC_URI[md5sum] = "e43a9416a6975e3be63d591bdeb33b04"
SRC_URI[sha256sum] = "702b5b0e9c040eb3af8e157453f38dd6f53e1dcd8b1272d20266cda3d4372c8b"
