# include file for devel initramfs images

USE_DEVFS = "1"

# try to avoid dependency loops
EXTRA_IMAGEDEPENDS = ""

BASE_DEVEL_INSTALL = "\
    ${ROOTFS_BOOTSTRAP_INSTALL} \
    base-files \
    base-passwd \
    busybox \
    kernel-modules \
    procps \
    i2c-tools \
    mtd-utils \
    devmem2 \
    iperf3 \
    usbutils \
    hdparm \
    phytool \
    memtester \
    util-linux-sfdisk \
"

inherit image

IMAGE_LINGUAS = ""

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
IMAGE_FSTYPES:remove = " wic wic.* wic.xz wic.bmap tar.xz"
