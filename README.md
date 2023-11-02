## **Дипломный проект профессии "Тестировщик"**
### Описание приложения

 - Веб-сервис, который предлагает купить тур двумя способами:
1. Оплата через сайт с вводом данных владельца карты
2. Одобрение кредита на покупку при помощи введенных в форму данных владельца карты

### Задача

 - тестирования работы сервиса, автоматизация позитивных и негативных сценариев приобретения тура

### Необходимое ПО

 - JDK 11
 - IntellijIDEA
 - Docker Desktop
 - Веб-браузер

### Процедура запуска тестов

1. Склонировать проект из удаленного репозитория с помощью команды ```git clone``` в терминале
2. Открыть проект в IntellijIdea
3. Запустить Docker Desktop
4. Запустить контейнеры с помощью команды ``docker compose up -d``
5. Запустить приложение с помощью команды в первом терминале:
 - ``для MySQL: -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar``
 - ``для PostgreSQL: -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar``

6. Запустить тесты с помощью команд во втором терминале:
- ``для MySQL: ./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app``
- ``для PostgreSQL: ./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app``

7. Сформировать отчет о пройденных тестах - в терминале выполнить команду ``./gradlew allureServe``

8. Завершить работу allureServe ``CTRL+C``

9. Завершить работу тестируемого приложения в первом терминале ``CTRL+C``

10. Остановить работу контейнеров с помощью команды в терминале ``docker compose down``