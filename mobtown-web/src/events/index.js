const angular = require('angular')
const service = require('./events.service')
const failedEventComponent = require('./failed-event.component')
const savedEventComponent = require('./saved-event.component')
const newEventComponent = require('./new-event.component')

const events = angular
  .module('mobtown.events', [])
  .factory('eventsService', service)
  .component('mtNewEvent', newEventComponent)
  .component('mtFailedEvent', failedEventComponent)
  .component('mtSavedEvent', savedEventComponent)

module.exports = events.name
