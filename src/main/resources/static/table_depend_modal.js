let showModal = document.getElementById('showModal');

let allRoles;
fetch('/roles')
    .then(response => response.json())
    .then(roles => allRoles = roles);

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
    let submit = showModal.querySelector('input[type="submit"]');

    let hf_patch = document.createElement('input');
    hf_patch.type = 'hidden';
    hf_patch.name = '_method';
    hf_patch.value = 'patch';
    let hf_delete = document.createElement('input');
    hf_delete.type = 'hidden';
    hf_delete.name = '_method';
    hf_delete.value = 'patch';

    if (deleteMode === "true") {
        modalTitle.textContent = 'Удалить пользователя';
        ip.setAttribute('hidden', true);
        lp.setAttribute('hidden', true);
        submit.className = 'btn btn-danger';
        submit.value = 'Удалить';
        document.querySelectorAll('.modal-body .form-control')
            .forEach(e => e.setAttribute('disabled', true));
        document.querySelector('.modal-dialog form').appendChild(hf_delete);
        )
    } else {
        modalTitle.textContent = 'Редактировать пользователя';
        ip.removeAttribute('hidden');
        lp.removeAttribute('hidden');
        submit.className = 'btn btn-success';
        submit.value = 'Редактировать';
        document.querySelectorAll('.modal-body .form-control')
            .forEach(e => e.removeAttribute('disabled'));
        iid.setAttribute('disabled', true);
    }

    iid.value = user.id;
    ifn.value = user.firstName;
    iln.value = user.lastName;
    ia.value = user.age;
    iun.value = user.username;
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
})