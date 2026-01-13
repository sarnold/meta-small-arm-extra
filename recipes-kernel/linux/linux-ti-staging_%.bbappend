# Use FIT Image format
KERNEL_IMAGETYPE:forcevariable = "fitImage"
KERNEL_CLASSES += "kernel-fitimage"

# Fix load addresses
UBOOT_LOADADDRESS = "0x80008000"
UBOOT_ENTRYPOINT = "0x80008000"

do_assemble_fitimage[depends] += "u-boot:do_deploy"
