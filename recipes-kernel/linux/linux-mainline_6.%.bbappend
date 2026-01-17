# this actually appends to meta-sunxi:linux-mainline
FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " \
    file://zram.cfg \
"
