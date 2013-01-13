### GWT Maven plugin - m2e extension

This project is a m2e (Maven integration for Eclipse) configurator for the [Maven GWT Plugin](http://mojo.codehaus.org/gwt-maven-plugin/).
When a Maven project using the Maven GWT plugin is loaded inside Eclipse with the Maven integration
for Eclipse, it will extract configuration information from the Maven GWT plugin and report it inside
Eclipse.

## Roadmap

# 1.0.0
The first version, 1.0.0, is soon to be published, will configure the generate directory for the i18n, generateAsync
and css goals as source folders inside Eclipse.
This extension will also participate into the Eclipse build lifecycle: the i18n and css goals will be launched as soon
as a file in the Maven resource folders (src/main/resources by default) has been modified. The generateAsync will be
launched as soon as a file in the Maven source folders (src/main/java by default) has been modified.

Google Plugin for Eclipse (version 3.1.0.v201208080120-rel-r37 and later) runs maven managed GWT projects out of the box. 
Projects have Google various natures and settings; client-side code change is applied after browser refresh, server-side change requires DevMode reload.
