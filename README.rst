======================
 meta-small-arm-extra
======================

Custom small ARM device layer with extra packages and config/package tweaks,
mostly for RPi, BBB, imx6 (nitrogen6x), and similar (small being relative ;)
where the emphasis is mainline support for kernel/u-boot/graphics, etc.

Newer work on rocko/dunfell branches supports mainly arm64 devices, including:

* marvell espressobin v5/v7 variants
* amlogic S905 (odroid-c2, nanopi-k2)
* allwinner A64/H5/H6 (pine64, pine64-plus, etc)

There is also some new machine support for espressobin (both legacy and
new device trees) and pine64/pine64-plus on mainline.  Some new "convenience"
images for router devices (mainly edgerouter and espressobin) were also added.

Previous work on morty for testing imx6 etnaviv graphics includes custom 
kernel / u-boot recipes for nitrogen6x (imx6q Boundary Devices machine).
Note the older kernels are still available but should get updates soon.

This layer also supports cubox-i (pro), wandboard, and udoo/udoo-neo imx6
machines with minor changes; there are updated packages for etnaviv
accelerated 2D/3D graphics and most of the build tweaks are currently
captured in the local.conf.boundary example.

See the `LinuxOnArm`_ wiki pages for `sabrelite`_ (the most similar to
nitrogen6x wrt to u-boot flash config) and `wandboard`_ for more details.

.. _LinuxOnArm: https://eewiki.net/display/linuxonarm/Home
.. _sabrelite: https://eewiki.net/display/linuxonarm/i.MX6+SABRE+Lite
.. _wandboard: https://eewiki.net/display/linuxonarm/Wandboard

* linux-armv7multi recipe supports the imx6 and other machines
* u-boot-nitrogen6 recipe is for bootscripts only since u-boot lives in SPI flash

There are some generic and machine-specific recipes here that can be used
with various poky or oe-core builds; tested on Beagles, RPi, edgerouter, CI-20,
esprssobin, pine64, odroid-c2/nanopi-k2, and many freescale machines.  This layer also
contains the minimal "machine" info required for beaglebone kernel and u-boot recipes
to build properly against oe-core and meta-ti, as well as a "baseline" machine
config for espressobin v5 (but is not yet a stand-alone BSP).

The older machine-specific support is for Nitrogen6, BeagleBone, and RaspberryPi, with
additional support planned for the future.

To Use This Layer for (fruit/nano) Pi Boards
============================================

Use the `vct FOSS clonepi bsp manifest`_ to clone the layers; current support
is mainly on the oe-dunfell branch, but will migrate to a newer branch soon.

Follow the "repo init, repo sync" process on the `manifest page`_ for the dunfell
branch and cd into the oe-core directory, then source the oe-init-build-env
file to create the build directory.

Before you edit the default conf files, make sure to review the examples in
the ``conf/`` folder.  The latest example config files are for building the
base LXDE image for a raspberrypi3-64bit machine. To use those examples,
perform the following:

* in meta-small-arm-extra/conf, copy ``conf/bblayers.conf.rpi-lxde``
  to your fresh build-dir/conf directory as bblayers.conf
* review your new local.conf and append the bits you need to the end
  of your fresh ``local.conf`` file.
* use ``bitbake lxde-dev-image`` to build an sdcard image

See also the upstream `git repo for meta-lxde`_

.. _vct FOSS clonepi bsp manifest: https://github.com/VCTLabs/vct-clonepi-bsp-platform
.. _git repo for meta-lxde: https://git.toradex.com/cgit/meta-lxde.git/git


If the download fails for the LXDE icon theme, please edit the recipe to use
the newer URL. Change to the ``meta-lxde`` folder and open the recipe file
``recipes-art/lxde-icon-theme/lxde-icon-theme_0.5.1.bb`` in your preferred
editor, then change the ``SRC_URI`` line to look like the following:

::

  #SRC_URI = "${SOURCEFORGE_MIRROR}/lxde/lxde-icon-theme-${PV}.tar.xz"
  SRC_URI = "http://downloads.sourceforge.net/project/lxde/LXDE%20Icon%20Theme/lxde-icon-theme-0.5.1/lxde-icon-theme-0.5.1.tar.xz"



To Use This Layer for Nitrogen6 Boards
======================================

Use the `vct FOSS boundary bsp manifest`_ to clone the layers; current support
is mainly on the oe-morty-test branch, but will migrate to the main branches
soon.

Follow the "repo init, repo sync" process on the `manifest page`_ for the morty
branch and cd into the poky/oe-core directory, then source the oe-init-build-env
file to create the build directory.

Before you edit the default conf files, make sure to:

* in meta-small-arm-extra/conf, copy local.conf.xxx and bblayers.conf.xxx
  to your fresh build-dir/conf directory as local.conf and bblayers.conf
* edit local.conf and set the paths for downloads, cache, and persistent dirs

  - in local.conf CORE_IMAGE_EXTRA_INSTALL you can delete the variables
    near the bottom to make a console image or leave them alone for X image
  - comment the DISTRO line to build oe-core "distroless"

* edit bblayers.conf and set the root path to your bsp clone directory
* cd back to top-level oe-core diectory and source oe-init-build-env build-dir again
* try "bitbake core-image-minimal"  
* **after** the build is finished, dd the sdcard image file to your card
* edit uEnv.txt on the boot partition to change video resolution
  (default is 1024x768, try your LCD resolution, eg, 1280x720 or 1920x1080)

.. _vct FOSS boundary bsp manifest: https://github.com/VCTLabs/vct-boundary-bsp-platform
.. _manifest page: https://github.com/VCTLabs/vct-boundary-bsp-platform/tree/oe-morty

For Udoo Neo boards, use the `foss-udoo-neo-platform-bsp`_ to clone the 
layers; current support is mainly on the poky-morty branch.  See also the
extra `sdcard doc for Udoo Neo`_ (includes Neo gadget ethernet/serial info).
The latter doc should end up in a git repo soon...

.. _foss-udoo-neo-platform-bsp: https://github.com/sarnold/foss-udoo-neo-platform-bsp
.. _sdcard doc for Udoo Neo: https://gist.github.com/sarnold/2e244fa8580ec715321a515c72535d4f


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


ARM64 U-Boot
============

For amlogic S905-based machines, install the ``u-boot.bin`` to an sdcard
device with ``dd``.  This depends somewhat on the board vendor's u-boot
blob(s); for the nanopi-k2 the following command works::

  $ DEV=/dev/your_sd_device
  $ dd if=u-boot.bin of=$DEV conv=fsync,notrunc bs=512 seek=1

The odroid-c2 is even more "special"::

  $ DEV=/dev/your_sd_device
  $ BL1=bl1.bin.hardkernel
  $ dd if=$BL1 of=$DEV conv=fsync bs=1 count=442
  $ dd if=$BL1 of=$DEV conv=fsync bs=512 skip=1 seek=1
  $ dd if=u-boot.img of=$DEV conv=fsync bs=512 seek=97


.. note:: The above filenames are produced via ``bitbake virtual/bootloader``
          and can be found in the image deploy directory after building.

More to come...

Enjoy!

