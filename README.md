
Library Project

Описание

Это проект для автоматизации учета в небольшой местной библиотеке. Система позволяет вести цифровой учет книг, читателей, а также отслеживать, кому из читателей выданы книги.

Этот проект был реализован мной бесплатно как новый опыт разработки. Для меня это был вызов, который помог освоить работу с Java, Spring и взаимодействием с базой данных с использованием JDBC API.

Функциональность
	•	Управление книгами:
	•	Добавление, редактирование и удаление книг.
	•	Привязка книги к читателю или отметка, что книга свободна.
	•	Отображение всех книг и информации о каждой.
	•	Управление читателями:
	•	Добавление, редактирование и удаление читателей.
	•	Отображение всех читателей и их данных.
	•	Отображение списка книг, выданных конкретному читателю.
	•	Валидация данных:
	•	Проверка корректности вводимых данных для книг и читателей.
	•	Вывод сообщений об ошибках на страницах форм.
	•	Обработка ошибок:
	•	Все исключения обрабатываются глобальным обработчиком и отображаются на отдельной странице с сообщением об ошибке.

Использованные технологии
	•	Backend:
	•	Java 17
	•	Spring Boot 3.4
	•	Spring MVC
	•	JDBC API (для работы с базой данных)
	•	PostgreSQL (в качестве базы данных)
	•	Frontend:
	•	Thymeleaf (для шаблонов HTML)
	•	Стили и доработки интерфейса были добавлены фронтенд-разработчиком.

Как запустить проект
	1.	Убедитесь, что у вас установлены Java 17, PostgreSQL и Maven.
	2.	Создайте базу данных в PostgreSQL (название library_db).
	3.	Настройте подключение к базе данных в файле application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=ваш_пользователь
spring.datasource.password=ваш_пароль
spring.jpa.hibernate.ddl-auto=update


	4.	Запустите проект с помощью Maven:

mvn spring-boot:run


	5.	Откройте приложение в браузере по адресу: http://localhost:8080.

Личное впечатление

Для меня этот проект стал важным этапом в освоении Spring Framework и работы с базами данных через JDBC API. Я научился:
	•	Работать с шаблонизатором Thymeleaf.
	•	Реализовывать валидацию данных.
	•	Использовать JDBC API для работы с базой данных.
	•	Обрабатывать ошибки и настраивать удобные страницы для пользователей.

Это был интересный и полезный опыт, который я с удовольствием применю в будущих проектах!

