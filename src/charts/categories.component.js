const data = require('../fake-data')
const template = require('./categories.tpl.html')

class CategoriesController {
  constructor () {
    const series = data.map(d => d.type).reduce((series, type) => {
      series.set(type, (series.has(type) ? series.get(type) : 0) + 1)
      return series
    }, new Map())
    // categories is an array of categories in descending order of event count
    this.categories = new Array(...series.entries())
      .sort(([k1, v1], [k2, v2]) => v1 < v2)
      .map(([key, value]) => key)
    this.data = {
      labels: new Array(...series.keys()),
      series: new Array(...series.values())
    }
    this.options = {
      labelDirection: 'explode',
      labelOffset: 20
    }
  }
}

module.exports = {
  template,
  controller: CategoriesController
}
