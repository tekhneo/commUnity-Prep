  // Import the functions you need from the SDKs you need
  import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-app.js";
  import { getAuth, signInWithEmailAndPassword, onAuthStateChanged } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-auth.js";


  // Your web app's Firebase configuration
  const firebaseConfig = {
    apiKey: "AIzaSyAwAapZ_7K5D4B960hHiu868n6L5CUb0Bk",
    authDomain: "community-prep-1024.firebaseapp.com",
    projectId: "community-prep-1024",
    storageBucket: "community-prep-1024.appspot.com",
    messagingSenderId: "184163988138",
    appId: "1:184163988138:web:7773c99913d33b855156d3"
  };

  // Initialize Firebase
  const app = initializeApp(firebaseConfig);
  const auth = getAuth();

// Set an authentication state observer and get user data
onAuthStateChanged(auth, (user) => {
  if (user) {
      // User is signed in, you can access user data here
      console.log("User signed in:", user);
      // For example, you can get the user's email
      const email = user.email;
      alert(`Welcome, ${email}!`);
  } else {
      // User is signed out
      console.log("No user is signed in.");
      alert("No user is signed in.")
  }
});

// submit button and fuction to run after button is pressed
const submit = document.getElementById('submit'); 
submit.addEventListener("click", function (event) {
    event.preventDefault()
    // inputs
    const email = document.getElementById('email-input').value;
    const password = document.getElementById('password-input').value;

    // fuction creates account wiht email and password

    signInWithEmailAndPassword(auth, email, password)
  .then((userCredential) => {
    alert("Login successful!");
    // Signed in
    window.location.href = "home.html"; //takes you to home page after login
    const user = userCredential.user;
    // User data is already accessible via onAuthStateChange
  })
  .catch((error) => {
    const errorCode = error.code;
    const errorMessage = error.message;
    alert("***WRONG user/password***\n" + errorMessage)
  });
    
})