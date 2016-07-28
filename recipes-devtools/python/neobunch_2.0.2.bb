SUMMARY = "A dot-accessible dictionary (a la JavaScript objects)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f0291cd32b2d6a91d9b19970c94d0a46"

SRC_URI = "https://github.com/F483/neobunch/archive/v${PV}.tar.gz"

SRC_URI[md5sum] = "487cc5023ba049fdf1bdd3a9e71280f2"
SRC_URI[sha256sum] = "f5a3eac5ba1413efd3754b4d21c1137653d33d67240f00edfb2e169db782cbc3"

inherit setuptools

RDEPENDS_${PN} = "python-six"
