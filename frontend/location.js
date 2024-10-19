//Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-app.js";
//import { getAuth, createUserWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-auth.js";

const firebaseConfig = {
    apiKey: "AIzaSyAwAapZ_7K5D4B960hHiu868n6L5CUb0Bk",
    authDomain: "community-prep-1024.firebaseapp.com",
    projectId: "community-prep-1024",
    storageBucket: "community-prep-1024.appspot.com",
    messagingSenderId: "184163988138",
    appId: "1:184163988138:web:7773c99913d33b855156d3"
  };

// Initialize Firebase
//const app = firebase.initializeApp(firebaseConfig);
//const database = firebase.database();

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

                const dbRef = ref(database, 'community-prep-1024-default-rtdb/data/~2F');
                const data = coordinates;
                set(dbRef, data)
                
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
