const angular = require('angular')
const component = require('./mobtown.component')
// side-effect only require styles
require('./styles.scss')

const mobtown = angular
  .module('mobtown', [])
  .component('mobtown', component)

module.exports = mobtown.name
