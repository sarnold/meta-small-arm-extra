======================
 meta-small-arm-extra
======================

Custom small ARM device layer with extra packages and config/package tweaks,
mostly for RPi, BBB, imx2x, Neo, and similar (small being relative ;)

Now includes custom kernel / u-boot recipes based on the work of Robert C.
Nelson (eg, see the `LinuxOnArm`_ wiki page for `BeagleBoneBlack`_) for
current beaglebone machines (white, black, green, and shortly blue)..

.. _LinuxOnArm: https://eewiki.net/display/linuxonarm/Home
.. _BeagleBoneBlack: https://eewiki.net/display/linuxonarm/BeagleBone+Black

* linux-boneblack recipe depends on Yocto/Poky (meta-yocto-bsp) layers
* linux-bb-kernel recipe depends on OE-core and either meta-ti or meta-beagleboard BSP layers

More to come...

Enjoy!

