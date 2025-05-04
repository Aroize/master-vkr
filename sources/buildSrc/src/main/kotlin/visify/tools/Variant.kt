package visify.tools

class Variant(
    val name: String,
    val isMinifyEnabled: Boolean,
    val projectRelativeProguard: String
) {
    companion object {
        @JvmField
        val Debug = Variant(
            name = "debug",
            isMinifyEnabled = false,
            projectRelativeProguard = "proguard-rules.pro"
        )
        @JvmField
        val Release = Variant(
            name = "release",
            isMinifyEnabled = true,
            projectRelativeProguard = "proguard-rules.pro"
        )
    }
}