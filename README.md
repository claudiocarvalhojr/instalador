# installer
Installer for jar files, one can create the folder and shortcut to the application based on previously established settings in properties file.

How to use:

- Create the lib folder at the root of the application.

- Add the following jar files to the lib folder and to the project build path:

    - commons-io-2.4.jar or higher https://commons.apache.org/proper/commons-io/

    - jshortcut-0.4-oberzalek.jar https://github.com/jimmc/jshortcut
    
- Create properties file in your project, with the following structure:

  - nameApp = My App (application name)

  - output = c:\\\\my_app (folder where the application will be installed)

  - fileJar = my_app.jar (name of the executable jar file, with the extension)

  - fileIcon = my_icon.ico (icon file name, with extension)

  - pathIcon = /main/resources/img/ (relative path to the icon file in your project)

- Pass the relative path of your properties file in the initialize method call, eg: Installer.initialize ("main/resources/properties/config.properties");

- Generate jar (not executable), without the jars files that are in the lib folder. eg: installer-1.0.jar
