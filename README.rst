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

There are also some generic and machine-specific recipes here that can be used
with various poky or oe-core builds; tested on Beagles, RPi, edgerouter, CI-20,
and many freescale machines (imx233 to imx6quad).  This layer also contains
the minimal "machine" info required for beaglebone kernel and u-boot recipes
to build properly against oe-core and meta-ti (but is not a stand-alone BSP).

The current machine-specific support is for BeagleBone and RaspberryPi, with
additional support planned for the future.

To Use This Layer for BeagleBone
================================

This layer can be used manually by cloning it into your oe-core or poky source
tree, add it to bblayers.conf, and set your preferred providers for u-boot and
the kernel (almost all additional layers can be setup this way).  For an easy
alternative use the `VCT Beagleboard BSP Manifest`_ to grab all the required
layers for either an oe-core "distro-less" build or a poky distro build (the
latter uses the `Yocto Project`_ reference BSP (meta-yocto-bsp is part of the
poky metadata collection).

.. _VCT Beagleboard BSP Manifest: https://github.com/VCTLabs/vct-beagleboard-bsp-platform
.. _Yocto Project: https://git.yoctoproject.org/cgit/cgit.cgi/

To use this layer with the above manifest repo, open the `VCT Beagleboard BSP Manifest`_
page on github and select the desired branch, eg, poky-krogoth for the krogoth
release of poky and related layers. then follow the steps to initialize and sync
all of the layers.

* If you select a poky-* branch and use meta-yocto-bsp, then

  - add meta-small-arm-extra to bblayers **below** the meta-yocto-bsp layer
  - set your preferred kernel to linux-boneblack

* If you select an oe-core-* branch and use meta-ti or meta-beagleboard, then

  - add meta-small-arm-extra to bblayers **above** the meta-ti/bb layer
  - set your preferred kernel to linux-bb-kernel

Both of the above kernel recipes pull the same linux-stable tree, checkout the
same branch, and apply the same patches (however, both also track the branch
HEAD so the build commit will change over time).

Note that all three possible BSP's for the beaglebone machine have slightly
different machine definitions, metadata, etc, so if you get unexpected results
you might want to try switching the BSP layer (you should only include one BSP
layer at a time in your bblayers.conf file).  Also note that you can use any of
the three BSP layers with poky, but since oe-core does not include a reference
BSP you will need to add either meta-ti or meta-beagleboard/common-bsp to your
bblayers.conf.

Since your build config is your own, any required additional layers will depend
on what image you want to build and any additional packages you want to install.
The above configuration requirements are illustrated below; they assume you
followed the BSP manifest steps for ``repo init`` and ``repo sync``.

Full config file examples for poky-krogoth:

* `local.conf`_
* `bblayers.conf`_

.. _local.conf: https://gist.github.com/sarnold/55d55bbf355ccc9d8d8d09d35f993959
.. _bblayers.conf: https://gist.github.com/sarnold/431831e6cec25b678f5a9e521af12a8a

Example poky config snippets for bblayers.conf::

  POKYROOT = "/home/user/beagleboard-bsp/poky"
  
  BBLAYERS ?= " \
    ${POKYROOT}/meta \
    ${POKYROOT}/meta-poky \
    ${POKYROOT}/meta-yocto-bsp \
    ${POKYROOT}/meta-small-arm-extra \
    ${POKYROOT}/meta-openembedded/meta-oe \
    ...


and for local.conf::

  ...
  PREFERRED_PROVIDER_virtual/kernel = "linux-boneblack"
  PREFERRED_VERSION_linux-boneblack = "4.6%"
  PREFERRED_PROVIDER_virtual/bootloader = "u-boot"
  PREFERRED_VERSION_virtual/bootloader = "2016.03%"
  ...


Example oe-core config snippets for bblayers.conf::

  OEROOT = "/home/user/beagleboard-oecore/oe-core"
  
  BBLAYERS ?= " \
    ${OEROOT}/meta \
    ${OEROOT}/meta-small-arm-extra \
    ${OEROOT}/meta-ti \
    ${OEROOT}/meta-openembedded/meta-oe \
    ...


and for local.conf::

  ...
  PREFERRED_PROVIDER_virtual/kernel = "linux-bb-kernel"
  PREFERRED_VERSION_linux-bb-kernel = "4.6%"
  PREFERRED_PROVIDER_virtual/bootloader = "u-boot"
  PREFERRED_VERSION_virtual/bootloader = "2016.03%"
  ...


More to come...

Enjoy!

