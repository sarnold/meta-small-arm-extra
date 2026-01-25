# make files in the current directory available to the recipe
FILESEXTRAPATHS:prepend := "${THISDIR}:"

# append the configuration fragment to the source files
SRC_URI:append = "file://fragment.cfg file://files/general-server.cfg file://files/main.cfg \
        file://0001-Add-call-for-post-update-script.patch"

do_install:append() {
        # install the general configuration file for swupdate
        install -d ${D}${sysconfdir}/swupdate/
        install -m 0644 ${WORKDIR}/files/main.cfg ${D}${sysconfdir}/swupdate/

        # and replace the placeholders with the actual values
        sed -i 's|@@IMAGE@@|${IMAGE_IDENTIFIER}|g' ${D}${sysconfdir}/swupdate/main.cfg
        sed -i 's|@@DEVICE@@|${MACHINE}|g' ${D}${sysconfdir}/swupdate/main.cfg
        sed -i 's|@@CURRENT_VERSION@@|${IMAGE_VERSION}|g' ${D}${sysconfdir}/swupdate/main.cfg

        # install the configuration file for the swupdate daemon
        install -d ${D}${sysconfdir}/swupdate/conf.d
        install -m 0644 ${WORKDIR}/files/general-server.cfg ${D}${sysconfdir}/swupdate/conf.d
        sed -i -e "s|/etc/|${sysconfdir}/|" ${D}${sysconfdir}/swupdate/conf.d/general-server.cfg

        # and replace the placeholders with the actual values
        if [ -z "${SWUPDATE_SERVER_ADDRESS}" ]; then echo -e "\nVariable SWUPDATE_SERVER_ADDRESS is not set." >&2; exit 1; fi
        sed -i "s|@@SWUPDATE_SERVER_ADDRESS@@|${SWUPDATE_SERVER_ADDRESS}|g" ${D}${sysconfdir}/swupdate/conf.d/general-server.cfg

        # write the hardware revision to the default hwrevision file; this would need to be adapted
        # for a real use case, to retrieve the hardware revision from a pin configuration for example
        install -d ${D}${sysconfdir}
        echo "${MACHINE} 1.0" > ${D}${sysconfdir}/hwrevision
}
