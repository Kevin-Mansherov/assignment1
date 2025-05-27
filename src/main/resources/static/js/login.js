
//login function
async function login(event) {
  event.preventDefault();
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  console.log("username: ",username);
  console.log("password: ",password);

  const response = await fetch("http://localhost:8080/api/users/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  });

  if(response.ok){
    console.log("Login successful");
    const message = await response.text();
    alert(message);
    window.location.href = "index.html"
  }else{
    console.log("Login failed");
    const error = await response.text();
    alert(error);
  }
}