SUMMARY = "Pillow is the friendly PIL (where PIL is the Python Imaging Library)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=511fa97b9f2063fcbb87b52973174032"

SRC_URI = "https://github.com/python-pillow/Pillow/archive/${PV}.tar.gz;downloadfilename=Pillow-${PV}.tar.gz"

SRC_URI[md5sum] = "56c2f864d9a64cb5491410024d755229"
SRC_URI[sha256sum] = "ae2fbb500c81100f7e374545d008019e6f90918386f620625c3b3b98faf88414"

#inherit  distutils python-dir
inherit setuptools python-dir

S = "${WORKDIR}/Pillow-${PV}"

DEPENDS = "lcms freetype libpng jpeg zlib tiff python-cython-native libwebp"
DEPENDS += "python-dateutil python-pytz python-six python-native \
            python-jinja2 python-markupsafe python-docutils \
            python-requests python-pyflakes python-pep8"

# this is in the upstream docs but no configure exists  :(
EXTRA_OECONF = " --disable-platform-guessing \
    --disable-tcl --disable-tk --disable-imagequant \
    --disable-webp --disable-webpmux --disable-jpeg2000 "

DISTUTILS_INSTALL_ARGS += "--skip-build"

CFLAGS += "-fno-strict-aliasing -I${STAGING_INCDIR} -I${STAGING_INCDIR}/freetype2"
LDFLAGS += "-L${STAGING_LIBDIR} -L${STAGING_BASELIBDIR}"

do_compile_prepend() {
    sed -i -e s:/usr/include:${STAGING_INCDIR}: \
        -e s:/usr/local/include:${STAGING_INCDIR}: \
        -e s:/usr/local/lib64:${STAGING_LIBDIR}: \
        -e s:/usr/lib64:${STAGING_LIBDIR}: \
        -e s:/lib64:${STAGING_BASELIBDIR}: \
        ${S}/setup.py
}

do_compile() {
    #distutils_do_compile
    CFLAGS="${CFLAGS} -I${STAGING_INCDIR}" MAX_CONCURRENCY=1 \
        python setup.py build_ext --disable-platform-guessing \
        --disable-imagequant --disable-webp --disable-webpmux \
        --disable-jpeg2000 

    CFLAGS="${CFLAGS} -I${STAGING_INCDIR}" MAX_CONCURRENCY=1 \
        python setup.py build_scripts
}

