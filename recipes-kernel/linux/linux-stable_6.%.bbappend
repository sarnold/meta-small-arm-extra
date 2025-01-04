SECTION = "kernel"
SUMMARY = "Kernel config for rockchip on mainline/stable"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rockchip = " \
    file://defconfig \
"
