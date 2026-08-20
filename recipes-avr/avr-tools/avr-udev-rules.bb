SUMMARY = "AVR udev rules allow unpriviledged users acces to AVR dev tools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit allarch

SRC_URI = "file://60-avr-dev-devices.rules"
PV = "0.1"

do_install () {
    install -d ${D}${sysconfdir}/udev/rules.d
    
    # Path discovery logic for compatibility between Walnascar and older versions
    if [ -f ${UNPACKDIR}/60-avr-dev-devices.rules ]; then
        RULES_FILE=${UNPACKDIR}/60-avr-dev-devices.rules
    elif [ -f ${WORKDIR}/60-avr-dev-devices.rules ]; then
        RULES_FILE=${WORKDIR}/60-avr-dev-devices.rules
    fi

    if [ -n "$RULES_FILE" ]; then
        install -m 0644 $RULES_FILE ${D}${sysconfdir}/udev/rules.d/
    else
        bbfatal "60-avr-dev-devices.rules not found in UNPACKDIR (${UNPACKDIR}) or WORKDIR (${WORKDIR})"
    fi
}
