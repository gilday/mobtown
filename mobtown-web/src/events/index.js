const angular = require('angular')
const fakeEventsService = require('./fake-events.service')

const charts = angular
  .module('mobtown.events', [])
  .factory('eventsService', events)

module.exports = charts.name
