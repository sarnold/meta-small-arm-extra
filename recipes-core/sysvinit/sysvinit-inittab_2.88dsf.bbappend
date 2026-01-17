do_install:append() {
    #sed -e 's,^id:5:initdefault:$,id:3:initdefault:,' -i ${D}${sysconfdir}/inittab
    echo "S:5:respawn:${base_sbindir}/getty ttyS0 115200" >> ${D}${sysconfdir}/inittab
}

COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i|sun8i|ti)"
