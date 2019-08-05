# Android TV automation
Android TV automation using Appium

##Prerequisites
1. Install Java 8 - android tools are not working with newer version yet
2. Download appium
3. Download android studio
4. brew install node # get node.js
5. npm install -g appium # get appium
6. npm install -g appium-doctor
7. npm install wd # get appium client
8. Install Carthage
9. Set environment variables: export JAVA_HOME=$(/usr/libexec/java_home -v 1.8) export PATH=$PATH:$JAVA_HOME/bin export
 ANDROID_HOME=/Users//Library/Android/sdk export PATH=$PATH:$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$ANDROID_HOME/build- tools:$ANDROID_HOME/tools/bin
10. Create emulator with Android TV and API level-26

##Execute Test suite
`mvn clean test`

## Android TV project
https://github.com/Kentico/cloud-sample-app-android-tv