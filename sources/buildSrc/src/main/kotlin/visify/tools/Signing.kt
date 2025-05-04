package visify.tools

class Signing(
    val type: String,
    val storeFilename: String,
    val alias: String,
    val password: String
) {

    companion object {
        @JvmField
        val ClientDebug = Signing(
            type = "debug",
            storeFilename = "signing/debug.keystore",
            alias = "androiddebugkey",
            password = "android"
        )
        @JvmField
        val ClientRelease = Signing(
            type = "release",
            storeFilename = "signing/release.keystore",
            alias = "androidreleasekey",
            password = "android"
        )
        @JvmField
        val BusinessDebug = Signing(
            type = "debug",
            storeFilename = "signing/debug.keystore",
            alias = "businessdebugkey",
            password = "businessdebug"
        )
    }
}