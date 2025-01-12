SECTION = "kernel"
SUMMARY = "Kernel config for rockchip on mainline/stable"

KCONFIG_MODE = "alldefconfig"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rockchip = " \
    file://defconfig \
"

SRC_URI:append = " \
    file://zram.cfg \
"
