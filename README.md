# instalador
Installer for jar files, one can create the folder and shortcut to the application based on previously established settings in properties file.

How to use:

- Create the lib folder at the root of the application.

- Add the following jar files to the lib folder and to the project build path:

  - commons-io-2.4.jar
  
  - jshortcut-0.4-oberzalek.jar
  
- Create properties file with the following structure:

  - nameApp = application name, eg: My App

  - output = folder where the application will be installed, eg C:\\my_app

  - fileJar = jar file name (with the .jar extension), eg my_app.jar

  - fileIcon = icon file name (with .ico extension), eg: my_icon.ico

  - pathIcon = relative path to the icon file in your project, eg: /main/resources/img/

- Pass relative path of properties file in static initialize method, eg: Installer.initialize ("main/resources/properties/config.properties");

- Generate jar (not executable), without the jars files that are in the lib folder.
