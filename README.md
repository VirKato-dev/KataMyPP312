## Практическая задача 3.1.5
### Условие:
Склонируйте проект по ссылке и просмотрите его.

Модуль Spring Security позволяет нам внедрять права доступа, а также контролировать их исполнение без ручных проверок.
Spring Security базируется на 2х интерфейсах, которые определяют связь сущностей с секьюрностью: UserDetails и GrantedAuthority.
UserDetails - то, что будет интерпретироваться системой как пользователь.
GrantedAuthority - сущность, описывающая права юзера.
Оба эти интерфейса имеют множество реализаций: просмотрите класс WebSecurityConfig, в методе configure() с помощью настроек userDetailsService() мы собираем единственный на всю программу экземпляр UserDetails с именем и паролем user , а его роль “USER” так же будет преобразована в экземпляр GrantedAuthority.

Это простейший способ создания секьюрности. Так же мы можем использовать jdbc-аутентификацию путем написания запроса, возвращающего пользователя и роль.
Как вы понимаете, такие способы максимально просты, но лишены достаточной гибкости, потому наиболее часто используемый вариант настройки выглядит как имплементация UserDetails и GrantedAuthority в классах-сущностях с переопределением существующих методов.

#### Задание:
1. Написать Rest-контроллеры для вашего приложения.
2. Переписать вывод (заполнение) таблицы, модальных окон и т.д. на JS c помощью Fetch API, допускается использование JQuery.
3. При любых изменениях данных страница приложения не должна перезагружаться!

Ссылки:
- [Fetch](https://learn.javascript.ru/fetch)
- [статья на habr](https://habr.com/ru/company/otus/blog/440046/)
