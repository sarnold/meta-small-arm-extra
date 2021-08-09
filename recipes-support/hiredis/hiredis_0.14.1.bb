SUMMARY = "Minimalistic C client library for the Redis database"

HOMEPAGE = "https://github.com/redis/hiredis"
SECTION = "Development/Libraries"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=d84d659a35c666d23233e54503aaea51"

DEPENDS = "redis"

SRCREV = "685030652cd98c5414ce554ff5b356dfe8437870"
SRC_URI = "git://github.com/redis/hiredis;protocol=git \
           file://0001-Makefile-remove-hardcoding-of-CC.patch"

S = "${WORKDIR}/git"

inherit pkgconfig

CPPFLAGS_APPEND = " -D_GNU_SOURCE"
EXTRA_OEMAKE = "AR='${AR}' CC='${CC}' PREFIX=${prefix} LIBRARY_PATH=${baselib}"

do_compile() {
    oe_runmake dynamic static hiredis.pc
}

do_install_prepend() {
    export INSTALL='cp -r'
}

