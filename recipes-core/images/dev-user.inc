#IMAGE_CLASSES:append = " extrausers"
inherit extrausers

# plain text input => temppwd
# generated with openssl passwd
DIGEST_PASSWD ?= "\$5\$7gouZk3ag8XK0IBN\$0C.HE6IvqANEKOvtg5KkmBxAp0OC6m5X5xfIaciAGR5"

ROOT_DEFAULT_PASSWORD ?= "${DIGEST_PASSWD}"
DEFAULT_ADMIN_ACCOUNT ?= "devel"
DEFAULT_ADMIN_GROUP ?= "sudo"
DEFAULT_ADMIN_ACCOUNT_PASSWORD ?= "${DIGEST_PASSWD}"

set_sudoers_rules () {
    #!/bin/sh -e
    echo '%sudo ALL=(ALL) ALL' > ${IMAGE_ROOTFS}/etc/sudoers.d/devel
}

set_sshd_config_perms () {
    #!/bin/sh -e
    if [ "${@bb.utils.contains('IMAGE_FEATURES', 'ssh-server-openssh', 'yes', 'no', d)}" = "yes" ]; then
        chmod 0600 ${IMAGE_ROOTFS}/etc/ssh/sshd_config*
    fi
}

set_ssh_keys () {
    #!/bin/sh -e
    if [ ! -s ${HOME}/.ssh/id_rsa_devimage ]; then
        mkdir -p ${HOME}/.ssh/
        /usr/bin/ssh-keygen -b 4096 -t rsa -C "devel user" -f ${HOME}/.ssh/id_rsa_devimage
    fi
    mkdir -p ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    cp ${HOME}/.ssh/id_rsa_devimage.pub  ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chmod 700 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh
    chmod 600 ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}/.ssh/authorized_keys
    chown ${UID_FOR_AUTH}:${UID_FOR_AUTH} -R ${IMAGE_ROOTFS}/home/${USER_FOR_AUTH}
}

EXTRA_USERS_PARAMS = "${@bb.utils.contains('DISABLE_ROOT', 'True', "usermod -L root;", "usermod -p '${ROOT_DEFAULT_PASSWORD}' root;", d)}"

EXTRA_USERS_PARAMS:append = " useradd  ${DEFAULT_ADMIN_ACCOUNT};"
EXTRA_USERS_PARAMS:append = " groupadd  ${DEFAULT_ADMIN_GROUP};"
EXTRA_USERS_PARAMS:append = " usermod -p '${DEFAULT_ADMIN_ACCOUNT_PASSWORD}' ${DEFAULT_ADMIN_ACCOUNT};"
EXTRA_USERS_PARAMS:append = " usermod -aG ${DEFAULT_ADMIN_GROUP}  ${DEFAULT_ADMIN_ACCOUNT};"

ROOTFS_POSTPROCESS_COMMAND += "set_sudoers_rules; set_ssh_keys; set_sshd_config_perms;"
