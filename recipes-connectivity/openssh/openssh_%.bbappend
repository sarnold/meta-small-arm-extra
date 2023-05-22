RDEPENDS:${PN}-sshd += "${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam-plugin-nologin', '', d)}"
