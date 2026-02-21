IMAGE_CLASSES:append = " extrausers"

DIGEST_PASSWD ?= "\$5\$7gouZk3ag8XK0IBN\$0C.HE6IvqANEKOvtg5KkmBxAp0OC6m5X5xfIaciAGR5"

# DISABLE_ROOT is one of the hardening vars in meta-security/meta-hardening
# if not True, the above example temporary passwd is temppwd
#
EXTRA_USERS_PARAMS = "${@bb.utils.contains('DISABLE_ROOT', 'True', "usermod -L root;", "usermod -p '${DIGEST_PASSWD}' root;", d)}"
