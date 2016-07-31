EXTRA_AUTORECONF += "--exclude=autopoint,autoheader"

EXTRA_OECONF_append_class-target = " --enable-dynamic --enable-fontconfig \
--disable-compile-inits --with-ijs --enable-cups --enable-dbus --with-pdftoraster"

