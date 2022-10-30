let showModal = document.getElementById('showModal');

showModal.addEventListener('show.bs.modal', function (event) {
    // Button that triggered the modal
    let button = event.relatedTarget;
    // Extract info from data-bs-* attributes
    let user = JSON.parse(button.getAttribute('data-bs-whatever'));
    let deleteMode = button.getAttribute('data-bs-delete');
    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.

    // Update the modal's content.
    let modalTitle = showModal.querySelector('.modal-title');
    let iid = showModal.querySelector('#iid');
    let ifn = showModal.querySelector('#ifn');
    let iln = showModal.querySelector('#iln');
    let ia = showModal.querySelector('#ia');
    let iun = showModal.querySelector('#iun');
    let ip = showModal.querySelector('#ip');
    let lp = showModal.querySelector('#lp');
    let ir = showModal.querySelector('#ir');
    let submit = showModal.querySelector('button[type="submit"]');

    try {
        document.querySelector('input[name="_method"]').remove();
    } catch (e) {
    }

    if (deleteMode === "true") {
        document.querySelectorAll('.modal-body .form-control')
            .forEach(e => e.setAttribute('disabled', true));
        modalTitle.textContent = 'Удалить пользователя';
        ip.setAttribute('hidden', true);
        lp.setAttribute('hidden', true);
        submit.className = 'btn btn-danger';
        submit.textContent = 'Удалить';
        submit.addEventListener('click', e => {
            let url = '/admin';
            let requestBody = JSON.stringify({
                id: iid.value,
            });
            fetch(url, {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: requestBody
            })
                .then(reponse => {
                    getUsers();
                });
        });
    } else {
        document.querySelectorAll('.modal-body .form-control')
            .forEach(e => e.removeAttribute('disabled'));
        iid.setAttribute('disabled', true);
        modalTitle.textContent = 'Редактировать пользователя';
        ip.removeAttribute('hidden');
        lp.removeAttribute('hidden');
        submit.className = 'btn btn-success';
        submit.textContent = 'Редактировать';
        submit.addEventListener('click', e => {
            let url = '/admin';
            let requestBody = JSON.stringify({
                id: iid.value,
                username: iun.value,
                password: ip.value,
                age: ia.value,
                firstName: ifn.value,
                lastName: iln.value,
                roles: selectedRoles(ir.childNodes)
            });
            console.info(requestBody);

            fetch(url, {
                method: "PATCH",
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: requestBody
            })
                .then(reponse => {
                    getUsers();
                });
        });
    }

    iid.value = user.id;
    ifn.value = user.firstName;
    iln.value = user.lastName;
    ia.value = user.age;
    iun.value = user.username;
    ip.value = '';
    ir.replaceChildren(null);
    allRoles.forEach(role => {
        let opt = document.createElement('option');
        opt.innerText = role.authority.substring(5);
        opt.value = role.id;
        let has;
        user.roles.forEach(r => {
            if (r.authority === role.authority) has = true;
        });
        if (has) {
            opt.setAttribute('selected', true);
        } else {
            opt.removeAttribute('selected');
        }
        ir.appendChild(opt);
    });
});

/***
 * Получить список выбранных ролей
 * @param options
 * @return roles список
 */
function selectedRoles(options) {
    let roles = [];
    options.forEach(o => {
        if (o.selected) {
            roles.push(allRoles[o.value - 1]);
        }
    });
    return roles;
}