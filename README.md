# Dropwizard authentication quickstart

![BuildStatus](https://travis-ci.org/mariosk89/DropwizardAuthenticationQuickstart.svg?branch=master)

The project allows user to register and login a simple web-app. It also provides an administrator console that allows admins to promote other users to admins and to enable or disable their accounts. The project can provide a simple authentication mechanism on top of which other, richer projects, can be built.
To run the project you'll need Maven 3 and JDK8.

## Building

Build the project by running on your command line:
```
$ mvn clean install
```

## Running

Before runnint the project, you'll need to edit the contents of the config/properties.yml file and specify your desired port and seesion cookie name

Test the database migrations.
```
$ java -jar target/authentication-quickstart-1.0.0-SNAPSHOT.jar db migrate config/properties.yml --dry-run
```

If there are no errors, run the migration scripts.
```
$ java -jar target/authentication-quickstart-1.0.0-SNAPSHOT.jar db migrate config/properties.yml
```

([More information on the migration scripts](http://www.dropwizard.io/0.9.2/docs/manual/migrations.html))

If this is the first time running, set the admin password on the database

```
$ java -jar target/authentication-quickstart-1.0.0-SNAPSHOT.jar setuserpassword -u admin -p new_password_here config/local.yml
```

Run the application:
```
$ java -jar target/authentication-quickstart-1.0.0-SNAPSHOT.jar server config/local.yml
```
