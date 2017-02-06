# mobtown

Example full-stack web app built to demonstrate a curated set of technologies.
Mobtown explores Baltimore's public data sets for [Special Event
Permits](https://data.baltimorecity.gov/Public-Works/Special-Event-Permits/cdz5-3y2u/data)
and
[Arrests](https://data.baltimorecity.gov/Public-Safety/BPD-Arrests/3i3v-ibrt)
with the following technologies:

* AngularJS 1.6 with ui-router for structure on the front-end
* webpack for assembling the front-end single page app
* Babel for transpiling ES2015
* Bulma for styles and flexbox based layouts
* Jersey for a backend REST API
* HK2 for leightweight dependency injection
* Jackson for JSON marshalling in Java
* Hibernate for building a Java domain model of a MySQL database
* RxJava for observing API responses and database result sets as streams of
  events


## Getting Started (with docker)

Running mobtown with docker is the easiest way to get started because
it minimizes the number of dependencies you need to install and docker-compose
integrates all the mobtown services to work together for you.

1. install Java 8, docker, and docker-compose. The build assumes that your user
   has access to the default docker socket running on localhost
2. use the [Google API Console](https://console.developers.google.com/apis/dashboard)
   to generate a Google Maps JavaScript API key and set environment variable
   `MOBTOWN_GMAPS_API_KEY` to its value
3. `./gradlew build dockerBuildImage` to run all tests and build artifacts
   including docker images
4. `docker-compose up -d` to run all the mobtown docker images and their
   dependencies
5. browse to http://localhost:8000. Note: it may take some time for the ingest
   process to finish and the web server to start up. you can check the logs for
   those services using `docker-compose logs services` and `docker-compose logs
   ingest`


## Getting Started (no docker)

No docker? You can still run mobtown by running a few services and configuring
them to communicate with each other.

1. install Java 8
2. use the [Google API Console](https://console.developers.google.com/apis/dashboard)
   to generate a Google Maps JavaScript API key and set environment variable
   `MOBTOWN_GMAPS_API_KEY` to its value
3. run a MySQL service and create a database named "mobtown"
4. execute `./gradlew build assemble` to run all tests and build artifacts
5. unzip the archive in the `./mobtown-ingest/build/distributions` directory and
   execute the resulting `mobtown-ingest` script. Use `mobtown-ingest --help` to
   see database connection parameters. The ingest application will populate the
   "mobtown" MySQL database with data queried from openbaltimore.org
6. unzip the archive in the `./mobtown-services/build/distributions` directory
   and execute the resulting `mobtown-services` script. Use `mobtown-services
   --help` to see database connection parameters (same as `mobtown-ingest`).
   This app runs the mobtown HTTP API
7. execute `./gradlew mobtown-web:run` to start a development web server to serve the
   front-end. The development web server includes a reverse proxy for proxying
8. browse to http://localhost:8000/


## Why is it called "mobtown"?

"Mobtown" is a [historical nickname for Baltimore, MD](https://en.wikipedia.org/wiki/List_of_city_nicknames_in_Maryland).
This example web app explores Baltimore's [Special Events Permits](https://data.baltimorecity.gov/Public-Works/Special-Event-Permits/cdz5-3y2u/data)
public data set. One might playfully refer to a rowdy festival in Baltimore as a
"mob"

