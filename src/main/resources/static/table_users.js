/***
 * Заполнить таблицу всех пользователей
 */
function getUsers() {
    fetch('/admin')
        .then(response => response.json())
        .then(users => {
            document.getElementById('table-users').innerHTML = '';
            users.forEach(user => {
                addTr('table-users', user, true);
            });
        });
}


/***
 * Добавить колонку в строку таблицы
 * @param tr строка
 * @param style стиль колонки
 * @param text информация в ячейке
 */
function addTd(tr, style, text) {
    let td = document.createElement('td');
    td.className = style;
    td.innerHTML = text;
    tr.append(td);
}


/***
 * Заполнить информацию в профиле
 * @param id название таблицы, в которую вставляем строку
 * @param user данные о пользователе
 * @param mode режим отображения таблицы (true - для админа, false - для пользователя)
 */
function addTr(id, user, mode) {
    let tr = document.createElement('tr');
    addTd(tr, 'col-1', user.id);
    addTd(tr, 'col-2 text-nowrap', user.firstName);
    addTd(tr, 'col-2 text-nowrap', user.lastName);
    addTd(tr, 'col-1', user.age);
    addTd(tr, 'col-2 text-nowrap', user.username);
    let rolesText = '';
    user.roles.forEach(role => {
        rolesText += `${role.authority.substring(5)} `;
    });
    addTd(tr, 'col-sm-2 col-xl-1 text-nowrap', rolesText);

    if (mode) { // добавить кнопки каждому пользователю в таблице
        addTd(tr, 'col-1', `<a type="button" class="btn btn-sm btn-info text-light" data-bs-toggle="modal"\n` +
            `data-bs-target="#showModal" data-bs-whatever=${JSON.stringify(user)}>Edit</a>`);
        addTd(tr, 'col-1', `<a type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal"\n` +
            `data-bs-target="#showModal" data-bs-delete="true" data-bs-whatever=${JSON.stringify(user)}>Delete</a>`);
    }

    document.getElementById(id).appendChild(tr);
}
