saveenv
fdt addr ${fdt_addr} && fdt get value bootargs /chosen bootargs
if env exists partnum; then echo Booting from mmcblk0p${partnum}; else setenv partnum 2; echo partnum not set, default to ${partnum}; fi
load mmc 0:${partnum} ${kernel_addr_r} boot/@@KERNEL_IMAGETYPE@@
setenv bootargs "${bootargs} root=/dev/mmcblk0p${partnum}"
@@KERNEL_BOOTCMD@@ ${kernel_addr_r} - ${fdt_addr}
