#IMAGE_CLASSES:append = " extrausers"
inherit extrausers

DIGEST_PASSWD ?= "\$5\$7gouZk3ag8XK0IBN\$0C.HE6IvqANEKOvtg5KkmBxAp0OC6m5X5xfIaciAGR5"

USER_FOR_AUTH ?= "admin"
PASS_FOR_AUTH ?= "${DIGEST_PASSWD}"
UID_FOR_AUTH ?= "1009"

set_sudoers_rules () {
    #!/bin/sh -e
    echo '%sudo ALL=(ALL) ALL' > ${IMAGE_ROOTFS}/etc/sudoers.d/admin
    echo '%oscap ALL=(root) NOPASSWD: /usr/bin/oscap xccdf eval *' >> ${IMAGE_ROOTFS}/etc/sudoers.d/admin
}

set_sshd_config_perms () {
    #!/bin/sh -e
    if [ "${@bb.utils.contains('IMAGE_FEATURES', 'ssh-server-openssh', 'yes', 'no', d)}" = "yes" ]; then
        chmod 0600 ${IMAGE_ROOTFS}/etc/ssh/sshd_config*
    fi
}

set_ssh_keys () {
    #!/bin/sh -e
    if [ ! -s ${HOME}/.ssh/id_rsa_scanimage ]; then
        mkdir -p ${HOME}/.ssh/
        /usr/bin/ssh-keygen -b 4096 -t rsa -C "scan user" -f ${HOME}/.ssh/id_rsa_scanimage
    fi
    mkdir -p ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    cp ${HOME}/.ssh/id_rsa_scanimage.pub  ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chmod 700 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    chmod 600 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chown ${UID_FOR_AUTH}:${UID_FOR_AUTH} -R ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}
}

EXTRA_USERS_PARAMS = "${@bb.utils.contains('DISABLE_ROOT', 'True', "usermod -L root;", "usermod -p '${DIGEST_PASSWD}' root;", d)}"
EXTRA_USERS_PARAMS:append = " groupadd sudo; groupadd oscap; useradd -u ${UID_FOR_AUTH} -p '${PASS_FOR_AUTH}' -G sudo,oscap ${USER_FOR_AUTH}"

ROOTFS_POSTPROCESS_COMMAND:append = " set_sudoers_rules; set_ssh_keys; set_sshd_config_perms;"
