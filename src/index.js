const angular = require('angular')
const mobtown = require('./mobtown')

// add the root component then bootstrap angular
angular.element(document.body).prepend('<mobtown></mobtown>')
// add fontawesome cdn
angular.bootstrap(document, [mobtown])
