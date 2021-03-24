DEPENDS_append_a64 = " u-boot-tools-native"

ATF_DEPENDS ??= ""

EXTRA_OEMAKE_append_a64 = " BL31=${DEPLOY_DIR_IMAGE}/bl31-sun50i_a64.bin"
ATF_DEPENDS_a64 = " virtual/trusted-firmware-a:do_deploy"

do_compile[depends] .= "${ATF_DEPENDS}"

PACKAGES += "${PN}-conf"
PACKAGE_BEFORE_PN += "${PN}-conf"

ALLOW_EMPTY_${PN}-conf = "1"

FILES_${PN}-conf = "/boot/extlinux"
