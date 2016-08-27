FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI_append = "\
    file://0004-add-missing-define-to-input-h.patch \
   "
