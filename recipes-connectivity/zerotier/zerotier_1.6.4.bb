SUMMARY = "A Smart Ethernet Switch for Earth"
MAINTAINER = "https://github.com/zerotier/ZeroTierOne"
DESCRIPTION = "Create flat virtual Ethernet networks of almost unlimited size"
HOMEPAGE = "https://www.zerotier.com"
SECTION = "net"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=11bbae9cacaf61dd7fc10035f6f5c68e"

SRC_URI = "git://github.com/zerotier/ZeroTierOne.git;protocol=http;tag=1.6.4;branch=master;protocol=https \
	file://zerotier \
"

SRCREV = "${PV}"

S = "${WORKDIR}/git"

inherit update-rc.d systemd gitpkgv autotools-brokensep

INITSCRIPT_NAME = "zerotier"

TARGET_CC_ARCH += "${LDFLAGS}"
TARGET_CFLAGS += "-fpic"

RDEPENDS:${PN} = "kernel-module-tun"

do_install:append() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/zerotier ${D}/etc/init.d/zerotier
}
