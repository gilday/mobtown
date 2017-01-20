const angular = require('angular')
const uiRouter = require('angular-ui-router').default
const charts = require('../charts')
const events = require('../events')
const search = require('../search-events')
const mobtownComponent = require('./mobtown.component')
const homeComponent = require('./home.component')
const routes = require('./routes')
// side-effect only require styles
require('./styles.scss')

const mobtown = angular
  .module('mobtown', [charts, events, search, uiRouter])
  .component('mobtown', mobtownComponent)
  .component('home', homeComponent)
  .config(routes)

module.exports = mobtown.name
