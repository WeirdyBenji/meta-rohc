DESCRIPTION = "A library for the RObust Header Compression (ROHC) protocol. It provides an easy and robust way for applications to reduce their bandwidth usage on network links with limited capacity or expensive costs. Headers of network packets are compressed with the ROHC protocol and algorithms."
SUMMARY = "RObust Header Compression (ROHC) library"
HOMEPAGE = "https://rohc-lib.org"
BUGTRACKER = "https://rohc-lib.org/developer/bugtracker"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://rohc-lib.org/download/rohc-2.3.x/${PV}/rohc-${PV}.tar.xz"
SRC_URI[sha256sum] = "e5c3808518239a6a4673c0c595356d5054b208f32e39015a487a0490d03f9bec"
SRC_URI += "file://0001-fix-rohc_buf_-export-preventing-compilation-in-kerne.patch;subdir=rohc-${PV} \
            file://0003-fix-mod-file_operations-struct-to-proc_ops-and-corre.patch;subdir=rohc-${PV} \
            "

S = "${WORKDIR}/rohc-${PV}"

inherit module pkgconfig autotools-brokensep

## We define --with... because KERNEL_SRC may be not set
EXTRA_OECONF:append = " --enable-linux-kernel-module \
			--with-linux-kernel-src=${STAGING_KERNEL_DIR} \
			"

FILES:${PN} = "${libdir}/*"

## Makefile.am defines /lib/modules/$(uname -r) on the host but we want on the target /lib/modules/${KERNEL_VERSION}
do_install:append() {
	rohc_moddir=${D}/lib/modules/${KERNEL_VERSION}
	install -d ${rohc_moddir}/extra
	install -m 644 ${B}/linux/kmod/rohc.ko ${rohc_moddir}/extra/rohc.ko
	install -m 644 ${B}/linux/kmod/rohc_test.ko ${rohc_moddir}/extra/rohc_test.ko
}

RPROVIDES:${PN} += "kernel-module-${PN}"

