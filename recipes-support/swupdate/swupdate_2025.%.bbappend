FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://defconfig \
    file://enable-pre-post.cfg \
    file://fragment.cfg \
    file://main.cfg \
"
# file://0001-Add-call-for-post-update-script.patch

do_install:append() {
    install -d ${D}${sysconfdir}/swupdate/conf.d
    install -m 0644 ${WORKDIR}/main.cfg ${D}${sysconfdir}/swupdate/

    echo "${MACHINE} 1.0" > ${D}${sysconfdir}/hwrevision

    # fix root home directory in sysv init script
    sed -i -e "s|/home/root/|${ROOT_HOME}/|" ${D}${sysconfdir}/init.d/swupdate
}
