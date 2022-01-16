FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:beaglebone = "file://local-feeds.conf"
SRC_URI:append:raspberrypi = "file://local-feeds.conf"

do_configure:append() {
    if [ "${CUSTOM_FEED_CONFIG}" = "internal" ] ; then
        bbnote "Using package feed ${CUSTOM_FEED_URL}"
        sed -i \
            -e "s|www.gentoogeek.org|${CUSTOM_FEED_URL}|" \
            ${WORKDIR}/local-feeds.conf || bbnote "sed fail"
    fi
}

do_configure:append_raspberrypi() {
    if ${@bb.utils.contains('TUNE_FEATURES','arm1176jzfs','true','false',d)}; then
        sed -i \
            -e "s|armv6-vfp|arm1176jzfshf-vfp|g" \
            -e "s|rpi-ipk|rpihf-ipk|" \
            ${WORKDIR}/local-feeds.conf || bbnote "sed fail"
    fi
}

do_install:append:raspberrypi() {
    install -m 0644 ${WORKDIR}/local-feeds.conf ${D}${sysconfdir}/opkg/
}

do_install:append:beaglebone() {
    install -m 0644 ${WORKDIR}/local-feeds.conf ${D}${sysconfdir}/opkg/
}

