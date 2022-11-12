let allRoles;

function fetchAllRoles() {
    fetch('/roles')
        .then(response => response.json())
        .then(roles => allRoles = roles);
}


let roles = [];
//let user;

function fetchMyRoles() {
    fetch('/user')
        .then(response => response.json())
        .then(user => {
            this.user = user;
            roles = [];
            user.authorities.forEach(role => {
                roles.push(role.authority.replaceAll('ROLE_', ''));
            });
            showUserInfo();
            sidebar();
            if (contains(roles, 'ADMIN')) {
                getUsers();
            }
        });
}


function showUserInfo() {
    document.getElementById('headerUsername').innerHTML = `<b>${user.username}</b> имеет роли: ${roles}`;
    document.getElementById('table-user').replaceChildren('');
    addTr('table-user', user);

}


function sidebar() {
    let el;
    if (!contains(roles, 'ADMIN')) {
        document.getElementById('v-pills-admin-tab').setAttribute('hidden',true);
        document.getElementById('v-pills-admin').setAttribute('hidden', true);
        el = document.getElementById('v-pills-admin-tab');
        el.className = el.className.replaceAll(' active', '');
        el = document.getElementById('v-pills-admin');
        el.className = el.className.replaceAll(' show active', '');
        document.getElementById('v-pills-user-tab').setAttribute('active', 'true');
        document.getElementById('v-pills-user-tab').className += ' active';
        document.getElementById('v-pills-user').className += ' show active';
    } else {
        document.getElementById('v-pills-admin-tab').removeAttribute('hidden');
        document.getElementById('v-pills-admin').removeAttribute('hidden');
        document.getElementById('v-pills-admin-tab').className += ' active';
        document.getElementById('v-pills-admin').className += ' show active';
        document.getElementById('v-pills-user-tab').removeAttribute('active');
        el = document.getElementById('v-pills-user-tab');
        el.className = el.className.replaceAll(' active', '');
        el = document.getElementById('v-pills-user');
        el.className = el.className.replaceAll(' show active', '');
    }
}


function contains(list, element) {
    let has = false;
    list.forEach(el => {
        if (el === element) {
            has = true;
        }
    });
    return has;
}


fetchAllRoles();
fetchMyRoles();