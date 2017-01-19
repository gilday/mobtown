const angular = require('angular')
const charts = require('../charts')
const search = require('../search-events')
const component = require('./mobtown.component')
// side-effect only require styles
require('./styles.scss')

const mobtown = angular
  .module('mobtown', [charts, search])
  .component('mobtown', component)

module.exports = mobtown.name
