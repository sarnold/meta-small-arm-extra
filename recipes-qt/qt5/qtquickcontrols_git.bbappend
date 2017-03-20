FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

FILES_${PN} += " \
    ${OE_QMAKE_PATH_QML}/*/*/qml/*.ttf \
"
