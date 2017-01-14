const angular = require('angular')
const monthHistogram = require('./month-histogram.component')

const charts = angular
  .module('mobtown.charts', [])
  .component('monthHistogram', monthHistogram)

module.exports = charts.name
