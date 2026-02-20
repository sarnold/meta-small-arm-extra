FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://enable-fit.cfg"
SRC_URI:append:sunxi = " file://boot.cmd"

DEPENDS:append:a64 = " u-boot-tools-native"

ATF_DEPENDS ??= ""

EXTRA_OEMAKE:append:a64 = " BL31=${DEPLOY_DIR_IMAGE}/bl31-sun50i_a64.bin"
ATF_DEPENDS:a64 = " virtual/trusted-firmware-a:do_deploy"

do_compile[depends] .= "${ATF_DEPENDS}"

PACKAGE_BEFORE_PN += "${PN}-conf"

ALLOW_EMPTY:${PN}-conf = "1"

FILES:append:a64:${PN}-conf = "/boot/extlinux"
