import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * An empty plugin that can be applied in a project to gain access
 * to the Config composite build
 */
@Suppress("unused")
class ConfigPlugin: Plugin<Project> {
    override fun apply(target: Project) { }
}
