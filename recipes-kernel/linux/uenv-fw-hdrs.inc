# custom kernel include for beagle kernels
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI_append = " file://uEnv.txt  file://uEnv.txt.root"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-uenv"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_beaglebone = " kernel-firmware-am335x-pm"

do_install:append() {
	oe_runmake headers_install INSTALL_HDR_PATH=${D}${exec_prefix}/src/linux-${KERNEL_VERSION} ARCH=$ARCH

	echo "uname_r=${LINUX_VERSION}${LINUX_VERSION_EXTENSION}" >> ${WORKDIR}/uEnv.txt

	install -d ${D}/boot/
        install -m 0644 ${WORKDIR}/uEnv.txt ${D}/boot/uEnv.txt
	install -m 0644 ${WORKDIR}/uEnv.txt.root ${D}/uEnv.txt

	cd ${S}/firmware
	install -d ${D}/lib/firmware
	install -m 0644 -t ${D}/lib/firmware \
		am335x-pm-firmware.bin am335x-pm-firmware.elf am335x-bone-scale-data.bin

	rm -f ${D}${KERNEL_SRC_PATH}/arch/*/vdso/vdso*.so
}

do_deploy:append() {
	install -m 0644 -t ${DEPLOYDIR} ${WORKDIR}/uEnv.txt ${WORKDIR}/uEnv.txt.root
}

PACKAGES =+ "kernel-firmware-am335x-pm kernel-uenv kernel-headers"
FILES_kernel-uenv = "/boot/uEnv.txt /uEnv.txt"
FILES_kernel-headers = "${exec_prefix}/src/linux*"
FILES_kernel-firmware-am335x-pm = "/lib/firmware/am335x-pm-firmware.bin /lib/firmware/am335x-pm-firmware.elf /lib/firmware/am335x-bone-scale-data.bin"

