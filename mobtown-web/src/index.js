const angular = require('angular')
const mobtown = require('./mobtown')

// add the root component then bootstrap angular
angular.element(document.body).prepend('<mt-mobtown></mt-mobtown>')
angular.bootstrap(document, [mobtown], { strictDi: true })
