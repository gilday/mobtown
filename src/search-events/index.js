const angular = require('angular')
const mapComponent = require('./map.component')
const searchEventsComponent = require('./search-events.component')

const search = angular
  .module('mobtown.search', [])
  .component('map', mapComponent)
  .component('searchEvents', searchEventsComponent)

module.exports = search.name
