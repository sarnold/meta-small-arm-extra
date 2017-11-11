DISTRO_FEATURES_append = " opengl"
DISTRO_FEATURES_BACKFILL_CONSIDERED ?= "systemd"

#LOCALE_UTF8_ONLY = "1"
#LOCALE_UTF8_IS_DEFAULT = "1"
IMAGE_LINGUAS = "en-us en-gb de-de fr-fr es-mx pt-br it-it"
#LIMIT_BUILT_LOCALES = "POSIX en_US en_GB de_DE fr_FR es_MX pt_BR it_IT"

# for building morty on hardened
GCCVERSION = "6.2%"
SDKGCCVERSION = "${GCCVERSION}"
BINUVERSION = "2.27%"
GDBVERSION = "7.11%"
GLIBCVERSION = "2.24"

COMMERCIAL_AUDIO_PLUGINS = "gst-plugins-ugly-mad gst-plugins-ugly-mpegaudioparse"
COMMERCIAL_VIDEO_PLUGINS = "gst-plugins-ugly-mpeg2dec gst-plugins-ugly-mpegstream gst-plugins-bad-mpegvideoparse"

