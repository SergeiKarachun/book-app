# Задача
Разработка CRUD Web API для имитации библиотеки (создание, изменение, удаление,
получение), выполняется на Spring.

### Функционал Web API
1. Получение списка всех книг.
2. Получение определенной книги по ее Id;
3. Получение книги по ее ISBN;
4. Добавление новой книги.
5. Изменение информации о существующей книге.
6. Удаление книги. 
7. Получение списка свободных книг.
### Дополнительный функционал
1. Разработать дополнительный сервис(LibraryService)
2. При дабавлении ноовй книги в первый сервис, отправляется запрос(асинхронный), содержащий ID книги.
3. Новый сервис хранит информацию:
	а. О книге(ID)
	б. Время когда книгу взяли
	в. Время когда книгу нужно вернуть

### Используемые в проекте технологии
1. Spring Cloud
2. Spring Data
3. PostgreSQL
4. Athentication via bearer token
5. Swagger

### Документация к проекту (Руководство)
1. Запустить Docker desktop на своем компьютере
2. Start postgres instance, в терминале ввести  "docker run --name book-app -e POSTGRES_PASSWORD=postgres -p 5433:5432 -d postgres"
3. Скачать файл kafka-installation по пути https://github.com/SergeiKarachun/kafka-installation.git
4. Start kafka-installation instance, в терминале ввести "C:\{yourPath}\kafka-installation> docker compose -f docker-compose.yml up -d"
5. В Intellij IDEA подключиться к БД(url: jdbc:postgresql://localhost:5433/postgres
    				    username: postgres
    				    password: postgres)
 Создать таблицы в БД, выполнить файл "database-init.sql", находится в корне проекта book-app
6. Вставить данные в таблицы, выполнить файл "insert_data.sql", находится в корне проекта book-app
7. Запустить EurekaServerApplication.java
8. Запустить ApiGatewayApplication.java
9. Запустить IdentityServiceApplication.java
10. Запустить BookClientApplication.java
11. Запустить LibraryServiceApplication.java

### Микросервисы Book-app
1. eureka-server(порт:8761)  -  регистрирует сервисы
2. api-gateway(порт:8765)  -  обрабатывает запросы
3. identity-service(порт:9898) - IDP-сервер(сервис по сервис по созданию и атентификации юзера)
4. book-client(порт:8081)  -  CRUD сервис для книг и создания заказа на книгу
5. library-service(порт:8082)  - дополнительный сервис(LibraryService)

### Endpoints
Основные endpoints можно посмотреть по следующим путям:
1. http://localhost:8765/book-service/swagger-ui/index.html
2. http://localhost:8765/library-service/swagger-ui/index.html
3. http://localhost:8765/identity-service/swagger-ui/index.html

Для работы необходимо создать нового пользователя (http://localhost:8765/auth/register / POST).
После успешного создания получить Bearer token по пути (http://localhost:8765/auth/token  / POST).
Далее используя bearer token можно делать bookCRUD запросы.(http://localhost:8765/library-service/swagger-ui/index.html)- добавить токен атентификации.
Проверить валиден ли token можно по пути (http://localhost:8765/auth/validate?token={YOURTOKEN} / GET).
Изменяя добавляя/изменяя/удаляя книгу, создавая/изменяя заказ через book-service (http://localhost:8765/book-service/swagger-ui/index.html) данные автоматически добавляются в library-service (http://localhost:8765/library-service/swagger-ui/index.html)
