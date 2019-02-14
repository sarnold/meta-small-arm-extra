FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# this should probably use xserver-common instead of x11-common
RDEPENDS_${PN} = "${VIRTUAL-RUNTIME_xserver_common} xinit \
                  ${@oe.utils.conditional('ROOTLESS_X', '1', 'xuser-account', '', d)}"

