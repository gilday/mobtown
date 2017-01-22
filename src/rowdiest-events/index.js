const angular = require('angular')
const component = require('./rowdiest-events.component')

const rowdiest = angular
  .module('mobtown.rowdiest', [])
  .component('mtRowdiestEvents', component)

module.exports = rowdiest.name
