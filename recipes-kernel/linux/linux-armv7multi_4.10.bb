# custom oe-based kernel for upstream plus RCN kernel patch
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${BRANCH_VERSION}:"

include linux-armv7multi.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=nitrogen6x \
           http://rcn-ee.net/deb/xenial-armhf/${RELEASE_TAG}-${BB_VERSION}/patch-${LINUX_VERSION}-${BB_VERSION}.diff.gz;name=patch \
           file://0002-ARM-drm-armada-add-fix-for-build-error-missing-heade.patch \
           file://defconfig \
"

SRC_URI_append_nitrogen6x = "file://0001-imx6q-nitrogen6_max.dts-enable-spidev-on-ecspi5-disa.patch"

PATCHTOOL = "git"

BRANCH_VERSION = "4.10"
LINUX_VERSION = "${BRANCH_VERSION}.4"
RELEASE_TAG = "v${LINUX_VERSION}"
# KERNEL_TAG and RELEASE_TAG are normally in sync but sometimes the patch
# version lags behind the actual kernel tag
KERNEL_TAG = "v${BRANCH_VERSION}.4"
BB_VERSION = "armv7-x1"
LINUX_VERSION_EXTENSION = "-${BB_VERSION}"

SRCREV_nitrogen6x = "${KERNEL_TAG}"
KBRANCH_nitrogen6x = "linux-${BRANCH_VERSION}.y"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

SRC_URI[patch.md5sum] = "78f040e8a96015cf448e440139fcc259"
SRC_URI[patch.sha256sum] = "39afdc8ec1bdc24f096440b75739a5d97be03ca9ef2bdc10bf0a01b790e22684"
