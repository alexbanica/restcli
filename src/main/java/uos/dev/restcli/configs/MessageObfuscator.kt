package uos.dev.restcli.configs

interface MessageObfuscator {
    fun obfuscate(message: String): String
}

class DefaultMessageObfuscator(
    private val environmentConfigs: EnvironmentConfigs,
    private val decorator: PrivateConfigDecorator = ThreeStarConfigDecorator
) : MessageObfuscator {
    override fun obfuscate(message: String): String {
        return environmentConfigs.configs.entries.fold(message) { acc, entry ->
            val isPrivate = entry.value.isPrivate
            val value = entry.value.value
            if (isPrivate) acc.replace(value.toString(), decorator.decorate(value.toString())) else acc
        }
    }
}
