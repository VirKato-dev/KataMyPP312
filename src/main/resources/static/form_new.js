let formNew = document.getElementById('nav-new');
let nform = formNew.querySelector('form');
let nfn = formNew.querySelector('#fn');
let nln = formNew.querySelector('#ln');
let na = formNew.querySelector('#age');
let nun = formNew.querySelector('#mail');
let np = formNew.querySelector('#pw');
let nr = formNew.querySelector('#role');
let nsubmit = formNew.querySelector('button[type="button"]');

nsubmit.addEventListener('click', e => {
    let url = '/admin';
    let requestBody = JSON.stringify({
        id: null,
        username: nun.value,
        password: np.value,
        age: na.value,
        firstName: nfn.value,
        lastName: nln.value,
        roles: selectedRoles(nr.childNodes)
    });

    fetch(url, {
        method: "POST",
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
        body: requestBody
    })
        .then(reponse => {
            getUsers();
            nform.reset();
            document.querySelector('#nav-list-tab').click();
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
        if (o.selected) roles.push(allRoles[o.value - 1]);
    });
    return roles;
}