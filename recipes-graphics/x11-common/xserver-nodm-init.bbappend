FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RDEPENDS_${PN} = "xserver-common (>= 1.30) xinit \
                  ${@base_conditional('ROOTLESS_X', '1', 'xuser-account', '', d)}"

