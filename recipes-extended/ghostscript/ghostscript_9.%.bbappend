EXTRA_AUTORECONF += "--exclude=autopoint,autoheader"

EXTRA_OECONF:append:class-target = " --enable-dynamic --enable-fontconfig \
--disable-compile-inits --with-ijs --enable-cups --enable-dbus --with-pdftoraster"

