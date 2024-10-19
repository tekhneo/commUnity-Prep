  // Import the functions you need from the SDKs you need
  import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-app.js";
  import { getAuth, createUserWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-auth.js";



  // TODO: Add SDKs for Firebase products that you want to use
  // https://firebase.google.com/docs/web/setup#available-libraries

  // Your web app's Firebase configuration
  const firebaseConfig = {
    apiKey: "$YOUR_API_KEY",
    authDomain: "$YOUR_DOMAIN",
    projectId: "$YOUR_ID",
    storageBucket: "$YOUR_BUCKET",
    messagingSenderId: "$YOUR_SENDER_ID",
    appId: "YOUR_APP_ID"
  };

  // Initialize Firebase
  const app = initializeApp(firebaseConfig);

// submit button and fuction to run after button is pressed
const submit = document.getElementById('submit'); 
submit.addEventListener("click", function (event) {
    event.preventDefault()
    // inputs
    const email = document.getElementById('email-input').value;
    const password = document.getElementById('password-input').value;

    // fuction creates account wiht email and password
    const auth = getAuth();
    createUserWithEmailAndPassword(auth, email, password)
      .then((userCredential) => {
        // Popup after signup 
        const user = userCredential.user;
        alert("Creating Account...");
        window.location.href = "home.html"; //takes you to home page after login
        // ...
      })
      .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        alert(errorMessage);
        // ..
      });
})
