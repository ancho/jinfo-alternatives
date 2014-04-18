# Synopsis
This is a quick hack to install custom java [alternatives][DebianAlternatives] on debian based distros.

It parses jinfo-files and installs the described alternatives. This gives you the ability to switch easyly between different jvm packages with `updat-java-alternatives`

# Warning

This is a very quick hack. No validation. No Tests. No CommandLineArguments parsing.

# Installation

1. clone repository
2. run `gradle installApp`
3. cp -r build/install/jinfo-alternatives/ /usr/local/share/
4. ln -s /usr/local/share/jinfo-alternatives/bin/jinfo-alternatives /usr/local/bin/jinfo-alternatives

# Usage

`jinfo-alternatives /usr/lib/jvm/.java-7-oracle.jinfo

#Preperation

1. unzip your custom jvm to /usr/lib/jvm
2. make a copy of an existent jinfo file (ex. cp /usr/lib/jvm/.java-1.7.0-openjdk-amd64.jinfo /usr/lib/jvm/.java-7-oracle.jinfo)
3. replace name and correct path

# Why?

Yes. Good question.
There is a nice script to switch a complete java version with one Command. 
Its called update-java-alternatives (man update-java-alternatives) and is shipped with the java-common package.

For the two openjdk packages provided by debian this works out of the box. But if you install oracls jvm by hand
you have a lot of work to do.

1. Unzip the package to /usr/lib/jvm
2. Install update-alternives for every single package in jdk/bin/ or jdk/jre/bin

And if you want to switch the whole package you have to repeat a lot of update-alternative commands all over again.
Thats annoying. I just wanted to hit `update-java-alternatives -s java-oracl-8` and i'm done.

## Very short description of update-java-alternatives
update-java-alternatives reads information from <jname>.jinfo files in /usr/lib/jvm.
A jinfo-file describes alternatives for a java-package named by <jname>.

### Example

In /usr/lib/jvm is a jinfo-file named '.java-1.7.0-openjdk-amd64.jinfo' with the following content.

`name=java-7-openjdk-amd64
alias=java-1.7.0-openjdk-amd64
priority=1071
section=main

hl java /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java
hl keytool /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/keytool
hl pack200 /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/pack200
hl rmid /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/rmid
hl rmiregistry /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/rmiregistry
hl unpack200 /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/unpack200
hl orbd /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/orbd
hl servertool /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/servertool
hl tnameserv /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/tnameserv
hl jexec /usr/lib/jvm/java-7-openjdk-amd64/jre/lib/jexec
jre policytool /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/policytool
jdk appletviewer /usr/lib/jvm/java-7-openjdk-amd64/bin/appletviewer
jdk extcheck /usr/lib/jvm/java-7-openjdk-amd64/bin/extcheck
jdk idlj /usr/lib/jvm/java-7-openjdk-amd64/bin/idlj
jdk jar /usr/lib/jvm/java-7-openjdk-amd64/bin/jar
jdk jarsigner /usr/lib/jvm/java-7-openjdk-amd64/bin/jarsigner
jdk javac /usr/lib/jvm/java-7-openjdk-amd64/bin/javac
jdk javadoc /usr/lib/jvm/java-7-openjdk-amd64/bin/javadoc
jdk javah /usr/lib/jvm/java-7-openjdk-amd64/bin/javah
jdk javap /usr/lib/jvm/java-7-openjdk-amd64/bin/javap
jdk jcmd /usr/lib/jvm/java-7-openjdk-amd64/bin/jcmd
jdk jconsole /usr/lib/jvm/java-7-openjdk-amd64/bin/jconsole
jdk jdb /usr/lib/jvm/java-7-openjdk-amd64/bin/jdb
jdk jhat /usr/lib/jvm/java-7-openjdk-amd64/bin/jhat
jdk jinfo /usr/lib/jvm/java-7-openjdk-amd64/bin/jinfo
jdk jmap /usr/lib/jvm/java-7-openjdk-amd64/bin/jmap
jdk jps /usr/lib/jvm/java-7-openjdk-amd64/bin/jps
jdk jrunscript /usr/lib/jvm/java-7-openjdk-amd64/bin/jrunscript
jdk jsadebugd /usr/lib/jvm/java-7-openjdk-amd64/bin/jsadebugd
jdk jstack /usr/lib/jvm/java-7-openjdk-amd64/bin/jstack
jdk jstat /usr/lib/jvm/java-7-openjdk-amd64/bin/jstat
jdk jstatd /usr/lib/jvm/java-7-openjdk-amd64/bin/jstatd
jdk native2ascii /usr/lib/jvm/java-7-openjdk-amd64/bin/native2ascii
jdk rmic /usr/lib/jvm/java-7-openjdk-amd64/bin/rmic
jdk schemagen /usr/lib/jvm/java-7-openjdk-amd64/bin/schemagen
jdk serialver /usr/lib/jvm/java-7-openjdk-amd64/bin/serialver
jdk wsgen /usr/lib/jvm/java-7-openjdk-amd64/bin/wsgen
jdk wsimport /usr/lib/jvm/java-7-openjdk-amd64/bin/wsimport
jdk xjc /usr/lib/jvm/java-7-openjdk-amd64/bin/xjc
plugin mozilla-javaplugin.so /usr/lib/jvm/java-7-openjdk-amd64/jre/lib/amd64/IcedTeaPlugin.so`

To get a list of all available packages run

`update-java-alternatives -l

To switch a package run

`update-alternatives -s <name>`


# Links
[DebianAlternatives]: https://wiki.debian.org/DebianAlternatives

