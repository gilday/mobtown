function eventsServiceFactory ($http) {
  const eventsUrl = `/api/events`
  return {
    all () {
      return $http.get(eventsUrl, { cache: true })
        .then(response => response.data.map(e => {
          e.start = new Date(e.start)
          e.end = new Date(e.end)
          return e
        }))
    },

    get (permitID) {
      return $http.get(`${eventsUrl}/${permitID}`, { cache: true })
        .then(response => {
          const event = response.data
          event.arrests = event.arrests.map(a => {
            const re = /\((-?\d+\.\d+),(-?\d+\.\d+)\)/
            const [, lat, lng] = a.location.match(re)
            a.location = [parseFloat(lat), parseFloat(lng)]
            return a
          })
          return event
        })
    }
  }
}

module.exports = ['$http', eventsServiceFactory]
