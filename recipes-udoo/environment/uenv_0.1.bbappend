FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_udooneo = "file://uEnv.txt"

# we need to override the vendor uEnv for mainline
do_install_append_udooneo () {
    install -m 0755 ${WORKDIR}/uEnv.txt ${D}/boot
}

