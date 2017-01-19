const angular = require('angular')
const ngChartist = require('angular-chartist.js')
const monthHistogram = require('./month-histogram.component')
const categories = require('./categories.component')
require('./styles.scss')

const charts = angular
  .module('mobtown.charts', [ngChartist])
  .component('monthHistogram', monthHistogram)
  .component('categories', categories)

module.exports = charts.name
