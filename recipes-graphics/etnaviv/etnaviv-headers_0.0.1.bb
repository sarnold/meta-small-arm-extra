SUMMARY = "FOSS driver headers for the Vivante GCxxx series of embedded GPUs"
HOMEPAGE = "https://github.com/laanwj/etna_viv"
BUGTRACKER = "https://github.com/laanwj/etna_viv/issues"
SECTION = "x11/drivers"
LICENSE = "MIT-X"

LIC_FILES_CHKSUM = "file://LICENSE;md5=9d4853905d85f044ed013e75def30a76"

PE = "1"
PR = "r16"
BRANCH ="master"
SRCREV = "2e0e0e681becb9d092e4f3d601a540736d0b0350"

# this version is actually newer than the Sean Cross 1.1 release but
# follows the RCN deb package versioning -> 0.0.1-r16 is current

DEPENDS = "virtual/xserver libdrm"

SRC_URI = "git://github.com/laanwj/etna_viv;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}/usr/include/etnaviv
    install -t ${D}/usr/include/etnaviv "${S}"/src/etnaviv/*.h
    install -t ${D}/usr/include/etnaviv "${S}"/attic/etnaviv/*.h
}

ALLOW_EMPTY_${PN} = "1"
