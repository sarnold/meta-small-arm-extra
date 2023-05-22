FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

FILES:${PN} += " \
    ${OE_QMAKE_PATH_QML}/*/*/qml/*.ttf \
"
