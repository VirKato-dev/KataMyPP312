// Делаем кнопку 'Пользователь' активной, если нет кнопки 'Админ'
// Также делаем видимой панель относящуюся к кнопке 'Пользователь'
if (document.getElementById('v-pills-admin-tab') == null) {
    let state = document.getElementById('v-pills-user-tab').getAttribute('class').concat(' active');
    document.getElementById('v-pills-user-tab').setAttribute('class', state);

    state = document.getElementById('v-pills-user').getAttribute('class').concat(' show active');
    document.getElementById('v-pills-user').setAttribute('class', state);
}