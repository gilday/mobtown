const angular = require('angular')
const events = require('./events.service')

const charts = angular
  .module('mobtown.events', [])
  .factory('eventsService', events)

module.exports = charts.name