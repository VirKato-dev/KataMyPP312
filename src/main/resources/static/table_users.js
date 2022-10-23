function addTd(tr, style, text) {
    let td = document.createElement('td');
    td.className = style;
    td.innerText = text;
    tr.append(td);
}

function addTr(id, user, mode) {
    let tr = document.createElement('tr');
    addTd(tr, 'col-1', user.id);
    addTd(tr, 'col-2 text-nowrap', user.firstName);
    addTd(tr, 'col-2 text-nowrap', user.lastName);
    addTd(tr, 'col-1', user.age);
    addTd(tr, 'col-2 text-nowrap', user.username);
    let rolesText = '';
    user.roles.forEach(role => {
        rolesText += role.authority.substring(5) + ' ';
    });
    addTd(tr, 'col-sm-2 col-xl-1 text-nowrap', rolesText);

    if (mode) { // добавить кнопки

    }

    document.getElementById(id).appendChild(tr);
}

fetch('/admin')
    .then(response => response.json())
    .then(users => {
        users.forEach(user => {
            addTr('table-users', user);

        })
    });
