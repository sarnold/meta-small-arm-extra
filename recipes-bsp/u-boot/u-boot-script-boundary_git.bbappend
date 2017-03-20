COMPATIBLE_MACHINE = "(nitrogen6x|nitrogen6x-lite|nitrogen6sx|nitrogen7|imx6-acl)"

PV = "v2017.01+git${SRCPV}"

SRCREV = "1f2f81cc27b95a698d7bdbe15d2aca0ba181ce07"
SRCBRANCH = "v2017.01-nitro"
SRC_URI = "git://github.com/VCTLabs/u-boot.git;branch=${SRCBRANCH}"

BOOTSCRIPT = "${S}/board/boundary/nitrogen6x/6x_bootscript_uEnv.txt"

do_deploy_append() {
    install ${S}/board/boundary/${MACHINE}/uEnv.txt ${DEPLOYDIR}/
}
