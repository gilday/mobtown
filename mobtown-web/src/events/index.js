const angular = require('angular')
const service = require('./events.service')

const events = angular
  .module('mobtown.events', [])
  .factory('eventsService', service)

module.exports = events.name
