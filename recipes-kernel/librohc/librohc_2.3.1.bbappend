inherit module 

#SRC_URI += "file://0001-fix-rohc_buf_-export-preventing-compilation-in-kerne.patch;subdir=rohc-${PV} \
#            file://0002-fix-stdarg-not-found-for-those-who-need-specific-ker.patch;subdir=rohc-${PV} \
#            file://0003-fix-mod-file_operations-struct-to-proc_ops-and-corre.patch;subdir=rohc-${PV} \
#            "

#FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

## Might need to be redeclared if context is not kept
#S = "${WORKDIR}/rohc-${PV}"

## do ./configure --enable-linux-kernel-module; cf la doc
EXTRA_OECONF:append = " --enable-linux-kernel-module"
## Verbose make
#EXTRA_OEMAKE:append = " V=1"

## stdint cf <stdint-gcc.h> + if project includes are not set
EXTRA_OEMAKE:append = " CFLAGS+=-I${S}/linux/include"
#EXTRA_OEMAKE:append = " CFLAGS+=-I${S}/linux"
#EXTRA_OEMAKE:append = " CFLAGS+=-I${S}"
#EXTRA_OEMAKE:append = " CFLAGS+=-I${S}/src"
#EXTRA_OEMAKE:append = " CFLAGS+=-I${S}/src/common"
#EXTRA_OEMAKE:append = " CFLAGS+=-I${S}/src/comp"
#EXTRA_OEMAKE:append = " CFLAGS+=-I${S}/src/decomp"

## Should be set by ./configure --enable-linux-kernel-module; needed to know which include to use
EXTRA_OEMAKE:append = " CFLAGS+=-D__KERNEL__"

## It's automatic anyway, provider by kernel-module-split class
RPROVIDES:${PN} += "kernel-module-${PN}"

## Doesn't work, but already exported to /lib/modules/yocto-x.x.../extra/librohc.ko (modprobe available)
#KERNEL_MODULE_AUTOLOAD = ""
