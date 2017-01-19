/**
 * factory for succinctly creating a fake data point
 */
function event (name, type, start, end) {
  return {
    name,
    type,
    start: new Date(start),
    end: new Date(end)
  }
}

module.exports = [
  event('foo', 'Race', '2016-05-18', '2016-05-18'),
  event('bar', 'Race', '2016-04-18', '2016-05-18'),
  event('baz', 'Festival', '2016-06-18', '2016-05-18'),
  event('wibble', 'Festival', '2016-01-18', '2016-05-18'),
  event('wubble', 'Festival', '2016-02-18', '2016-05-18'),
  event('wobble', 'Block Party', '2016-08-18', '2016-05-18'),
  event('lorem', 'Unknown', '2016-05-18', '2016-05-18'),
  event('ipsum', 'Unknown', '2016-05-18', '2016-05-18'),
  event('dolor', 'Unknown', '2016-05-18', '2016-05-18'),
  event('sit amet', 'Unknown', '2016-05-18', '2016-05-18')
]
