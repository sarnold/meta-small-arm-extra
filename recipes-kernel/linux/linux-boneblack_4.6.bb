# custom yocto-based kernel for RCN bb-kernel repo and patch

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=http;branch=${KBRANCH};name=machine \
           http://rcn-ee.net/deb/xenial-armhf/v4.6.0-bone3/patch-4.6-bone3.diff.gz;name=patch \
           file://defconfig"

LINUX_VERSION ?= "4.6"
LINUX_VERSION_EXTENSION_append = "-bone3"

# Modify SRCREV to a different commit hash in a copy of this recipe to
# build a different release of the Linux kernel.
# tag: v4.2 64291f7db5bd8150a74ad2036f1037e6a0428df2
#SRCREV_machine="64291f7db5bd8150a74ad2036f1037e6a0428df2"
SRCREV_machine="${AUTOREV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

KBRANCH_beaglebone = "linux-4.6.y"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_beaglebone = "beaglebone"

S = "${WORKDIR}/git"


SRC_URI[patch.md5sum] = "f6de36b3215daf396ca202113e2cd18b"
SRC_URI[patch.sha256sum] = "ca3041543137709efca353c0640100201b3f1f25620f766e6d5cf11dc68274ba"
