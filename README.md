# Задание
[Дипломная задача](https://github.com/netology-code/qa-diploma)
# Описание приложения:
Представленное приложение является веб-сервисом, который предлагает два способа покупки тура по определенной цене: обычная оплата с использованием дебетовой карты и уникальная технология, предоставляющая кредит на основе данных банковской карты. Само приложение не обрабатывает информацию о картах, а передает ее банковским сервисам:

Сервису платежей, который затем передает данные в Payment Gate.
Кредитному сервису, который затем передает данные в Credit Gate.
Приложение должно сохранять информацию о том, был ли совершен успешный платеж и какой способ был использован. При этом само приложение не хранит данные о картах.

# Инструкция по запуску тестов:

1. Клонировать проект с помощью команды `git clone https://github.com/abashkaev/Diploma_project_software_testing.git`
2. Открыть проект в IntelliJ IDEA.
3. Запустить контейнеры с помощью команды `docker-compose up --build.`
4. Для запуска сервиса с указанием пути к базе данных, использовать следующие команды:
    - Для MySQL: `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar`
    - Для PostgreSQL: `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar`
5. SUT (System Under Test) доступен по адресу `http://localhost:8080/`
6. Запуск тестов рекомендуется выполнять с параметрами, указывая путь к базе данных в командной строке:
    - Для MySQL: `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
    - Для PostgreSQL: `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
7. Для создания отчета с использованием Allure после выполнения тестов, выполните команду: `./gradlew allureReport.`
8. После завершения тестирования завершите работу приложения (нажмите CTRL+C) и остановите контейнеры с помощью команды `docker-compose down`.

# Документация 
[План тестирования](https://github.com/abashkaev/Diploma_project_software_testing/blob/main/docs/TestAutomationPlan.md)  
[Отчет по тестирования](https://github.com/abashkaev/Diploma_project_software_testing/blob/main/docs/Report.md)  
[Отчет по автоматизации](https://github.com/abashkaev/Diploma_project_software_testing/blob/main/docs/Summary.md)
