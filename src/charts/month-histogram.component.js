const d3 = require('d3')
const data = require('../fake-data')

// const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']

class MonthHistogramController {
  constructor ($element) {
    this.svg = $element.find('svg')[0]
    // TODO flatMap the range of dates between start and end
    this.data = new Array(12).fill(0)
    d3.nest()
      .key(d => d.getMonth()).rollup(arry => arry.length)
      .entries(data.map(d => d.start))
      .forEach(d => {
        this.data[d.key] = d.value
      })
  }

  $onInit () {
    const width = 960
    const height = 500
    const xaxisHeight = 20
    const x = d3.scaleLinear()
      .domain([0, 11])
      .range([0, width])
    const y = d3.scaleLinear()
      .domain([0, d3.max(this.data)])
      .range([0, height - xaxisHeight])
    const chart = d3.select(this.svg)
      .attr('width', width)
      .attr('height', height)
    chart.selectAll('rect')
        .data(this.data)
        .enter()
        .append('svg:rect')
        .attr('x', (d, i) => x(i))
        .attr('y', d => height - xaxisHeight - y(d))
        .attr('height', y)
        .attr('width', 40)
        .attr('fill', '#cecece')
    const xaxis = d3.axisBottom(x)
    chart.append('g')
      .attr('class', 'x axis')
      .attr('transform', `translate(0, ${height - xaxisHeight})`)
      .call(xaxis)
  }
}

module.exports = {
  template: '<svg></svg>',
  controller: ['$element', MonthHistogramController]
}
