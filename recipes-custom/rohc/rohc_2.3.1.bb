DESCRIPTION = "A library for the RObust Header Compression (ROHC) protocol. It provides an easy and robust way for applications to reduce their bandwidth usage on network links with limited capacity or expensive costs. Headers of network packets are compressed with the ROHC protocol and algorithms."
HOMEPAGE = "https://rohc-lib.org"
BUGTRACKER = "https://rohc-lib.org/developer/bugtracker"
SUMMARY = "RObust Header Compression library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI:append = " https://rohc-lib.org/download/rohc-2.3.x/2.3.1/rohc-2.3.1.tar.xz"
SRC_URI[sha256sum] = "e5c3808518239a6a4673c0c595356d5054b208f32e39015a487a0490d03f9bec"

S = "${WORKDIR}/rohc-2.3.1"

inherit pkgconfig autotools

