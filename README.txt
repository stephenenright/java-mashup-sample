## Building The Project.
The project can be built with maven or eclipse (See Running the project below).

```
mvn package
```

The above command will create a dependency jar with all the of the dependencies required to run the project.   Alternatively the project can be built and run from inside eclipse (see below).


## Running the project.
To run the project unzip the the given zip file.   The project can be executed in a number of ways.

* From Eclipse
The project can be executed from inside eclipse. The project can be imported into eclipse as an existing maven project.   The project can be executed by running sample.ApiMashupApp from inside of eclipse.  An application.properties file must exist in the root of the project.
* Distribution Folder
The submitted zip file contains a dist folder with a built version of the project.   
To run the project update the settings in application.properties with the appropriate twitter authentication tokens. On the command line from inside the dist folder run:
java -cp lib/java-api-mashup-1.0-RELEASE-jar-with-dependencies.jar sample.ApiMashupApp
* Maven Build
Build the jar file with maven as mentioned above, create an application.properties file (see application.properties in root of zip file).  Execute the jar file as mentioned in 2 above (Distribution Folder).
