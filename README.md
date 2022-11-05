## Практическая задача
### Условие:
Склонируйте проект по ссылке и просмотрите его.

Модуль Spring Security позволяет нам внедрять права доступа, а также контролировать их исполнение без ручных проверок.
Spring Security базируется на 2х интерфейсах, которые определяют связь сущностей с секьюрностью: UserDetails и GrantedAuthority.
UserDetails - то, что будет интерпретироваться системой как пользователь.
GrantedAuthority - сущность, описывающая права юзера.
Оба эти интерфейса имеют множество реализаций: просмотрите класс WebSecurityConfig, в методе configure() с помощью настроек userDetailsService() мы собираем единственный на всю программу экземпляр UserDetails с именем и паролем user , а его роль “USER” так же будет преобразована в экземпляр GrantedAuthority.

Это простейший способ создания секьюрности. Так же мы можем использовать jdbc-аутентификацию путем написания запроса, возвращающего пользователя и роль.
Как вы понимаете, такие способы максимально просты, но лишены достаточной гибкости, потому наиболее часто используемый вариант настройки выглядит как имплементация UserDetails и GrantedAuthority в классах-сущностях с переопределением существующих методов.

### Рассмотрим приложение.
#### Новые классы:
- WebSecurityConfig- настройка секьюрности по определенным URL, а также настройка UserDetails и GrantedAuthority.
- LoginSuccessHandler - хэндлер, содержащий в себе алгоритм действий при успешной аутентификации. Например, тут мы можем отправить пользователя с ролью админа на админку после логина, а с ролью юзер на главную страницу сайта и т.п.

#### Задание:
1. Перенесите классы и зависимости из предыдущей задачи. ***(перенесены и переработаны)***
2. Создайте класс Role и свяжите User с ролями так, чтобы юзер мог иметь несколько ролей. ***(реализовано так чтобы роли не доблировались в таблице roles)***
3. Имплементируйте модели Role и User интерфейсами GrantedAuthority и UserDetails соответственно. Измените настройку секьюрности с inMemory на userDetailService. ***(интекфейся реализованы)***
4. Все CRUD-операции и страницы для них должны быть доступны только пользователю с ролью admin по url: /admin/. ***(обрабатываются через AdminController)***
5. Пользователь с ролью user должен иметь доступ только к своей домашней странице /user, где выводятся его данные. Доступ к этой странице должен быть только у пользователей с ролью user и admin. Не забывайте про несколько ролей у пользователя! ***(Обрабатывается ProfileController)***
6. Настройте logout с любой страницы с использованием возможностей thymeleaf. ***(не понял. сейчас работает через параметр URL)***
7. Настройте LoginSuccessHandler так, чтобы админа после аутентификации направляло на страницу /admin, а юзера на его страницу /user. ***(реализовано)***

### Замечания по коду учтены
1. Переименуй папку DAO как должно быть, если ты работаешь с репозиторием. ***(переименовано в repositories)***
2. Убрать закоментированный код. ***(сделано)***
3. **import javax.persistence.*;** - импорт всего пакета не нужно, импортировать нужно только те инструменты, которые используются, посмотреть во всех классах, так же удалить импорты которые не используются. ***(лишние импорты убраны. Импорт пакета persistence раскладывать не рекомендовано IDEA, так как используется более 3-х классов из пакета)***

### Добавил от себя
1. При запуске приложения добавляются роли и пользователь в базу данных (для первичного тестирования)
2. Таблицы дропаются во избежание проблем со структурой оставшейся от других задач