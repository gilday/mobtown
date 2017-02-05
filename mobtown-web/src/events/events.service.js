function eventsServiceFactory ($http) {
  const eventsUrl = `/api/events`
  return {
    all () {
      return $http.get(eventsUrl, { cache: true })
        .then(response => response.data.map(e => {
          // mutate event because it is not shared with any other functions
          e.start = new Date(e.start)
          e.end = new Date(e.end)
          return e
        }))
    },

    get (permitID) {
      return $http.get(`${eventsUrl}/${permitID}`, { cache: true })
        .then(response => response.data)
    }
  }
}

module.exports = ['$http', eventsServiceFactory]
