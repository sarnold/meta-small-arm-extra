# custom yocto-based kernel for upstream plus patches for mips64 and octeon
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=edgerouter \
           file://0001-MIPS-OCTEON-fix-platform-bus-probing.patch \
           file://0002-MIPS-OCTEON-mangle-port-fix-build-failure-with-VDSO-.patch \
           file://0003-MIPS64-MULTU-MADDU-MSUBU-emulation-bugfix.patch \
           file://0004-v4.8-rc-gpio-leds-broken-on-OCTEON.patch \
           file://0005-MIPS-OCTEON-enable-building-edgerouter-lite-er-100-d.patch \
           file://defconfig \
"

BRANCH_VERSION = "4.8"
LINUX_VERSION = "${BRANCH_VERSION}.0"
RELEASE_TAG = "v${LINUX_VERSION}-rc2"

# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the bone patch
# version lags behind the actual kernel tag
KERNEL_TAG = "${RELEASE_TAG}"
RC_VERSION = "rc2"
LINUX_VERSION_EXTENSION = "-${RC_VERSION}"

RDEPENDS_kernel-base += "kernel-devicetree"
KERNEL_DEVICETREE_edgerouter = "ubnt_e100.dtb octeon_3xxx.dtb"

SRCREV_edgerouter = "${KERNEL_TAG}"

PV = "${LINUX_VERSION}+git${SRCPV}"

KBRANCH_edgerouter = "master"

COMPATIBLE_MACHINE_edgerouter = "edgerouter"

S = "${WORKDIR}/git"

do_configure_append() {
	echo "CONFIG_LOCALVERSION="\"${LINUX_VERSION_EXTENSION}\" >> ${B}/.conf$
}

#SRC_URI[patch.md5sum] = "08140a75d99ab9680ad4a604f2ab864e"
#SRC_URI[patch.sha256sum] = "18f58d19853ab30493c3f394a1db119f28e5043910854273226305f4a79d7200"

