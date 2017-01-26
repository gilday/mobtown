const angular = require('angular')
const ngmap = require('ngmap')
const mapComponent = require('./map.component')
const searchEventsComponent = require('./search-events.component')
require('./styles.scss')

const search = angular
  .module('mobtown.search', [ngmap])
  .component('mtMap', mapComponent)
  .component('mtSearchEvents', searchEventsComponent)

module.exports = search.name
