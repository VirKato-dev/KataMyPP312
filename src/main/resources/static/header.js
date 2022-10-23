fetch('/user')
    .then(response => response.json())
    .then(user => {
        document.getElementById('headerUsername').innerText = user.username;
    });
