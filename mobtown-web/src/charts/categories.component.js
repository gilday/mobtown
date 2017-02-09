const template = require('./categories.tpl.html')

class CategoriesController {
  $onInit () {
    const series = this.events.map(d => d.type).reduce((series, type) => {
      const count = series.has(type) ? series.get(type) : 0
      series.set(type, count + 1)
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
  bindings: {
    events: '<'
  },
  controller: CategoriesController,
  template
}
