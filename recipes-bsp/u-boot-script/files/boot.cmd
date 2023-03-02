setenv image_name boot/Image
setenv fdt_name boot/armada-3720-espressobin.dtb
setenv console console=ttyMV0,115200 earlycon=ar3700_uart,0xd0012000
setenv bootargs $console root=/dev/mmcblk0p1 rw rootwait net.ifnames=0 biosdevname=0
mmc dev 0
ext4load mmc 0:1 $kernel_addr $image_name
ext4load mmc 0:1 $fdt_addr $fdt_name
booti $kernel_addr - $fdt_addr
