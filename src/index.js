const angular = require('angular')
const mobtown = require('./mobtown')
const charts = require('./charts')
const rowdiest = require('./rowdiest-events')

// add the root component then bootstrap angular
angular.element(document.body).prepend('<mobtown></mobtown>')
// add fontawesome cdn
angular.bootstrap(document, [mobtown, charts, rowdiest])
