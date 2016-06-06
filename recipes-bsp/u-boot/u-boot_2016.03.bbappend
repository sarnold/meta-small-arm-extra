# extra u-boot config for non-yocto builds

SPL_BINARY = "MLO"
UBOOT_SUFFIX = "img"
UBOOT_MACHINE = "am335x_evm_config"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

VER_TAG = "v2016.03"

SRC_URI_append_beaglebone = " https://rcn-ee.com/repos/git/u-boot-patches/${VER_TAG}/0001-am335x_evm-uEnv.txt-bootz-n-fixes.patch"

SRC_URI[md5sum] = "da31b8a724bcdd3773db88d388be9122"
SRC_URI[sha256sum] = "f7adec044e08709956e3f7b898080744c307d7d5601085e46841e210fa9d8006"
