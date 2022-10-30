let roles = [];
//let user;

fetch('/user')
    .then(response => response.json())
    .then(user => {
        this.user = user;
        user.roles.forEach(role => {
            roles.push(role.authority.substring(5));
        });
        showUserInfo();
        sidebar();
        if (contains(roles, 'ADMIN')) {
            getUsers();
        }
    });


function showUserInfo() {
    document.getElementById('headerUsername').innerHTML = `<b>${user.username}</b> имеет роли: ${roles}` ;
    addTr('table-user', user);

}


function sidebar() {
    if (!contains(roles, 'ADMIN')) {
        document.getElementById('v-pills-admin-tab').remove();
        document.getElementById('v-pills-admin').remove();
        document.getElementById('v-pills-user-tab').setAttribute('active', 'true');
        document.getElementById('v-pills-user-tab').className += ' active';
        document.getElementById('v-pills-user').className += ' show active';
    }
}

function contains(list, element) {
    let has = false;
    list.forEach( el => {
        if (el === element) {
            has = true;
        }
    });
    return has;
}
