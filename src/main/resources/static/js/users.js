//add user
async function addUser(){

}

//load users
async function loadUsers(){
    const response = await fetch("http://localhost:8080/api/users/allUsers");
    const users = await response.json();

    const userContainer = document.getElementById("users");

    users.forEach(user => {
        userContainer.innerHTML += `
            <div class="user-card>
                <p>username: ${user.username}</p>
                <p>password: ${'*'.repeat(user.password.length)}</p>
                <p>roles: ${user.roles}</p>
            </div>
        `;
    });
}


window.onload = ()=>{
    loadUsers();
}