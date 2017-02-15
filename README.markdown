# mobtown

[![Build Status](https://travis-ci.org/gilday/mobtown.svg?branch=master)](https://travis-ci.org/gilday/mobtown)

Example full-stack web app built to demonstrate a curated set of technologies.
mobtown explores Baltimore's public data sets for [Special Event
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


## Components

The mobtown application consists of several components

1. mobtown-ingest: a command line application which queries all the Special
   Events and Arrests from [Open Baltimore
   datasets](https://data.baltimorecity.gov). It uses the Socrata Java API to
   query the datasets, then saves the data models in MySQL using Java
   Persistence API (JPA)
2. mobtown-services: an HTTP API which provides access to the mobtown database
3. mobtown-web: a browser application for visualizing the mobtown database

The mobtown-ingest application populates the MySQL database. mobtown-web is a
static web application served by the NGINX web server. NGINX does double duty as
it also provides a reverse proxy for mobtown-services to address browsers'
[same-origin
policy](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy)


## Getting Started

mobtown uses Gradle to build its components and it uses docker-compose for
integration testing. This requires Java, Docker, and docker-compose to be
installed and configured. Since this is a nontrivial set of dependencies, there
is a Vagrantfile which defines a development VirtualBox VM for hacking on
mobtown.


### Building mobtown in a Vagrant VirtualBox VM (optional)

The Vagrantfile defines an Ubuntu Xenial Xerus (16.04) vagrant box running on
VirtualBox for hacking on mobtown. The ubuntu box installs Java 8 JDK,
docker-engine, and docker-compose so you can hack on and build mobtown without
mucking with your development environment. Vagrant will [use rsync to
share](https://www.vagrantup.com/docs/synced-folders/rsync.html) the
mobtown project directory with the VirtualBox VM in `/vagrant`. The mobtown
Vagrantfile uses rsync instead of the default VirtualBox shared directory due to
problems with using npm in a VirtualBox share; since Vagrant uses an rsync
share, any edits made in the Vagrant machine will not be automatically synced to
the host.  Vagrant will forward your host's port 9000 to the VirtualBox VM so
you can still test the app at http://localhost:9000

1. Install Vagrant, VirtualBox, and rsync
2. `vagrant up` to instantiate the box
3. `vagrant ssh` to connect to the box
3. Once connected to the vagrant box, `cd /vagrant` to access the project files
4. Follow "Getting Started (with docker)" starting with step (2)


### Getting Started (with docker)

Running mobtown with docker is the recommended way to get started because it
minimizes the number of dependencies you need to install and docker-compose
integrates all the mobtown services to work together for you.

1. install Java 8 JDK, docker, and docker-compose. Make sure the docker service is
   running. The build assumes that your user has access to the default docker
   socket running on localhost
2. use the [Google API Console](https://console.developers.google.com/apis/dashboard)
   to enable the Google Maps JavaScript API. Visit the Credentials section to generate an API key.
   Create the environment variable `MOBTOWN_GMAPS_API_KEY` and set its value to the API key
3. `./gradlew build dockerBuildImage` to run all tests and build artifacts
   including docker images
4. `docker-compose up -d` to run all the mobtown docker images and their
   dependencies
5. browse to http://localhost:9000. Note: it may take some time for the ingest
   process to finish and the web server to start up. you can check the logs for
   those services using `docker-compose logs services` and `docker-compose logs
   ingest`


### Getting Started (without docker)

No docker? You can still run mobtown by running a few services and configuring
them to communicate with each other.

1. install Java 8 JDK
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
   front-end. The development web server includes a reverse proxy for proxying.
   Because the development web server process runs as part of the gradle build
   when using the "run" target, the gradle build will appear to hang, but when
   it prints "webpack: Compiled successfully", then the webpack dev server has
   finished building and is accepting requests
8. browse to http://localhost:9000/


## Why is it called "mobtown"?

"mobtown" is a [historical nickname for Baltimore, MD](https://en.wikipedia.org/wiki/List_of_city_nicknames_in_Maryland).
This example web app explores Baltimore's [Special Events Permits](https://data.baltimorecity.gov/Public-Works/Special-Event-Permits/cdz5-3y2u/data)
public data set. One might playfully refer to a rowdy festival in Baltimore as a
"mob"
