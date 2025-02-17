function toggleForm() {
    let container = document.querySelector(".container");
    let formTitle = document.getElementById("form-title");
    let switchText = document.getElementById("switch-text");

    // Toggle class for animation
    container.classList.toggle("active");

    // Change text dynamically
    if (container.classList.contains("active")) {
        formTitle.innerText = "Sign Up";
        switchText.innerHTML = 'Already have an account? <a href="#" onclick="toggleForm()">Login</a>';
    } else {
        formTitle.innerText = "Login";
        switchText.innerHTML = 'Don\'t have an account? <a href="#" onclick="toggleForm()">Sign up</a>';
    }
}
