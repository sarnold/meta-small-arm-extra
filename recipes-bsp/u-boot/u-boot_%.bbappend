# extra u-boot config for non-yocto builds of beaglebone

IMAGE_BOOT_FILES_beaglebone = "u-boot.${UBOOT_SUFFIX} MLO"
IMAGE_BOOT_FILES = "u-boot.${UBOOT_SUFFIX}"

SPL_BINARY_beaglebone = "MLO"
UBOOT_SUFFIX = "img"
UBOOT_MACHINE = "am335x_evm_defconfig"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

VER_TAG = "v2019.04"

SRC_URI_append_beaglebone = " \
	https://github.com/eewiki/u-boot-patches/raw/master/${VER_TAG}/0001-am335x_evm-uEnv.txt-bootz-n-fixes.patch \
	https://github.com/eewiki/u-boot-patches/raw/master/${VER_TAG}/0002-U-Boot-BeagleBone-Cape-Manager.patch \
"

SRC_URI[md5sum] = "da31b8a724bcdd3773db88d388be9122"
SRC_URI[sha256sum] = "f7adec044e08709956e3f7b898080744c307d7d5601085e46841e210fa9d8006"
