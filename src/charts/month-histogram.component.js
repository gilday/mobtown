const data = require('../fake-data')
const template = require('./month-histogram.tpl.html')

const months = ['January', 'February', 'March', 'April', 'May', 'June', 'Juyl', 'August', 'September', 'October', 'November', 'December']
const abbreviations = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']

class MonthHistogramController {
  constructor () {
    const starts = data.map(d => d.start.getMonth())
    const series = starts.reduce((series, month) => {
      series[month] += 1
      return series
    }, new Array(12).fill(0))
    this.data = {
      labels: abbreviations,
      series: [series]
    }
    this.mostHappeningMonth = months[maxIndex(series)]
  }
}

/**
 * returns index of maximum value in the array
 */
function maxIndex (arry) {
  let index
  let max = 0
  for (let i = 0; i < arry.length; i++) {
    if (arry[i] > max) {
      index = i
      max = arry[i]
    }
  }
  return index
}

module.exports = {
  template,
  controller: MonthHistogramController
}
