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
* Jackson for JSON marshalling in Java
* Hibernate for building a Java domain model of a MySQL database
* RxJava for observing API responses and database result sets as streams of
  events


## Getting Started

1. install Java 8
2. install node.js and npm (tested with v7.3.0 and v3.10.10 respectively)
3. run a MySQL container using docker `docker run --name mysql -e MYSQL_ROOT_PASSWORD-password -d mysql`
3. use the [Google API
   Console](https://console.developers.google.com/apis/dashboard) to generate a
   Google Maps JavaScript API key and set environment variable
   `MOBTOWN_GMAPS_API_KEY` to its value
4. execute `./gradlew build shadowJar` to build the Java backend
5. execute `java -jar mobtown-ingest/build/libs/mobtown-ingest-0.0.1-SNAPSHOT-all.jar` to ingest Open Baltimore data into the MySQL database
4. execute `./gradlew run` to run the backend Java REST API locally
5. in directory `mobtown-web`, execute `npm install && npm start` to run the
   front-end development web server locally
6. Browse to http://localhost:9000/


## Why is it called "mobtown"?

"Mobtown" is a [historical nickname for Baltimore, MD](https://en.wikipedia.org/wiki/List_of_city_nicknames_in_Maryland).
This example web app explores Baltimore's [Special Events Permits](https://data.baltimorecity.gov/Public-Works/Special-Event-Permits/cdz5-3y2u/data)
public data set. One might playfully refer to a rowdy festival in Baltimore as a
"mob"

