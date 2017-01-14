const angular = require('angular')
const mobtown = require('./mobtown')

// add the root component then bootstrap angular
angular.element(document.body).prepend('<mobtown></mobtown>')
angular.bootstrap(document, [mobtown])
