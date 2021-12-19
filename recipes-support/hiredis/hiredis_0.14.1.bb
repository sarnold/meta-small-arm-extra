DESCRIPTION = "Minimalistic C client library for Redis"
HOMEPAGE = "http://github.com/redis/hiredis"
LICENSE = "BSD-3-Clause"
SECTION = "libs"
DEPENDS = "redis"

LIC_FILES_CHKSUM = "file://COPYING;md5=d84d659a35c666d23233e54503aaea51"
SRCREV = "f9717aed445547c8ab11a3a4117f648c06e62b56"
SRC_URI = "git://github.com/redis/hiredis;protocol=https;branch=staging \
           file://0001-Makefile-remove-hardcoding-of-CC.patch"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

EXTRA_OEMAKE = "PREFIX=${prefix} LIBRARY_PATH=${baselib}"

# By default INSTALL variable in Makefile is equal to 'cp -a', which preserves
# ownership and causes host-user-contamination QA issue.
# And PREFIX defaults to /usr/local.
do_install_prepend() {
    export INSTALL='cp -r'
}
