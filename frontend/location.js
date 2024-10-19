document.getElementById('get-location').addEventListener('click', () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;
                // Store latitude and longitude in an array
                // Our location: Latitude: 26.0698463, Longitude: -80.2383032
                const coordinates = [latitude, longitude];

                document.getElementById('location').innerHTML = 
                    `Latitude: ${latitude}, Longitude: ${longitude}`;
            },
            (error) => {
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                        document.getElementById('location').innerHTML = "User denied the request for Geolocation.";
                        break;
                    case error.POSITION_UNAVAILABLE:
                        document.getElementById('location').innerHTML = "Location information is unavailable.";
                        break;
                    case error.TIMEOUT:
                        document.getElementById('location').innerHTML = "The request to get user location timed out.";
                        break;
                    case error.UNKNOWN_ERROR:
                        document.getElementById('location').innerHTML = "An unknown error occurred.";
                        break;
                }
            }
        );
    } else {
        document.getElementById('location').innerHTML = "Geolocation is not supported by this browser.";
    }
});