SUMMARY = "APSW stands for Another Python SQLite Wrapper."
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=830db1ec625f8ee8996c4a07c6b8150c"

SRC_URI = "https://github.com/rogerbinns/apsw/archive/${PV}-r1.tar.gz;downloadfilename=apsw-${PV}-r1.tar.gz"

SRC_URI[md5sum] = "5e23e52b5393c4e627df1c14a9935531"
SRC_URI[sha256sum] = "503b4975c00a66e6f5aa5f4593a4141ef8f71c11928bc3e7d6a356e5476878e2"

inherit  setuptools

S = "${WORKDIR}/apsw-${PV}-r1"

