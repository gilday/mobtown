/**
 * factory for succinctly creating a fake data point
 */
function date (start, end) {
  return { start: new Date(start), end: new Date(end) }
}

module.exports = [
  date('2016-05-18', '2016-05-18'),
  date('2016-04-18', '2016-05-18'),
  date('2016-06-18', '2016-05-18'),
  date('2016-01-18', '2016-05-18'),
  date('2016-02-18', '2016-05-18'),
  date('2016-08-18', '2016-05-18'),
  date('2016-05-18', '2016-05-18'),
  date('2016-05-18', '2016-05-18'),
  date('2016-05-18', '2016-05-18'),
  date('2016-05-18', '2016-05-18')
]
