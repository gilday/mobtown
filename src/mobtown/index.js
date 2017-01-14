const angular = require('angular')
const component = require('./mobtown.component')

const mobtown = angular
  .module('mobtown', [])
  .component('mobtown', component)

module.exports = mobtown.name
