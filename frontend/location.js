//Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-app.js";
import { getAuth, signInWithEmailAndPassword, onAuthStateChanged } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-auth.js";
import { getDatabase, ref, set } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-database.js";


const firebaseConfig = {
    apiKey: "$YOUR_API_KEY",
    authDomain: "$YOUR_DOMAIN",
    projectId: "$YOUR_ID",
    storageBucket: "$YOUR_BUCKET",
    messagingSenderId: "$YOUR_SENDER_ID",
    appId: "$YOUR_APP_ID"
};

// Initialize Firebase
var app = initializeApp(firebaseConfig);
var auth = getAuth(app);
var database = getDatabase(app); // Correctly initialize the database

let latitude = 2.5;
let longitude = 5.5;
let coordinates = [10.5, 2.22];
let userId = 'your_user_id'; // Make sure to define userId

document.getElementById('get-location').addEventListener('click', () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                latitude = position.coords.latitude;
                longitude = position.coords.longitude;
                // Store latitude and longitude in an array
                // Our location: Latitude: 26.0698463, Longitude: -80.2383032
                coordinates = [latitude, longitude];

                document.getElementById('location').innerHTML = 
                    `Latitude: ${latitude}, Longitude: ${longitude}`;
                    //  sign i guess
                // Signed in
                var user = userCredential.user; // Get the user object
                var userId = user.uid; // Get the unique user ID

                // Now you can use userId to store user data in the database
                console.log("User ID:", userId);

                    // DATABASE STUFF
                var userRef = ref(database, 'users/' + userId); // Create a reference to the user's data
                // TEST
                document.getElementById('location1').innerHTML = `Test Coordinates: ${userRef}`;

                // Store coordinates in the database
                set(userRef, {
                    coordinates: coordinates
                })
                .then(() => {
                    console.log("User data saved successfully.");
                })
                .catch((error) => {
                    console.error("Error saving user data: ", error);
                });
    


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


// TEST
    console.log("Outputs: Hello, World! " + longitude);


