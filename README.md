### GWT Maven plugin M2Eclipse extension

This project is a M2Eclipse configurator for the Maven integration for Eclipse (M2Eclipse).
When a Maven project using the Maven GWT plugin is loaded inside Eclipse with the Maven inegration
for Eclipse, it will extract configuration information from the Maven GWT plugin and report it inside
Eclipse.

## Roadmap

# 1.0.0
The first version, 1.0.0, is soon to be published, will configure the generate directory for the i18n, generateAsync
and css goals as source folders inside Eclipse.
This extension will also participate into the Eclipse build lifecycle: the i18n and css goals will be launched as soon
as a file in the Maven resource folders (src/main/resources by default) has been modified. The generateAsync will be
launched as soon as a file in the Maven resource folders (src/main/resources by default) has been modified.
Please not that this 1.0.0 has no relation with the Google Eclipse tooling (GPE) so the Eclipse project will not have
the Google various natures and settings.

# 1.1.0
This second version will bring integration with the Google Eclipse tooling (GPE) if this latter has been installed on
your Eclipse.
