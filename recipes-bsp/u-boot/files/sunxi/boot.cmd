# Default to (primary) SD
rootdev=mmcblk0p2
if itest.b *0x28 == 0x02 ; then
	# U-Boot loaded from eMMC or secondary SD so use it for rootfs too
	echo "U-boot loaded from eMMC or secondary SD"
	rootdev=mmcblk1p2
fi
setenv bootargs console=${console} console=tty1 root=/dev/${rootdev} rootwait ro panic=10 ${extra} net.ifnames=0
load mmc 0:1 ${kernel_addr_r} image.ub || load mmc 0:1 ${fdt_addr_r} ${fdtfile} && load mmc 0:1 ${kernel_addr_r} uImage
bootm ${kernel_addr_r} || bootm ${kernel_addr_r} - ${fdt_addr_r}
