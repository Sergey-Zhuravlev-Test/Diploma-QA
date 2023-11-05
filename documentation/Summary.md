## *Отчет по итогам автоматизации тестирования*

### *Запланировано и реализовано*

Изначально запланировано к автоматизации 70 тест-кейсов. В ходе написания было принято решение разделить API-тесты и DB-тесты, чтобы упростить в будущем поиск ошибок, если тесты не будут проходить. В итоге было создано 6 тестовых классов, реализовано и автоматизировано 74 тест-кейса.

### *Сработавшие риски*

 - по причине отсутствия документации приходилось опытным путем выяснять, что является нормой в работе приложения, а что дефектом
 - не запускалось приложение, потребовалось искать информацию для решения этой проблемы, так как опыта с настройкой запуска приложения, написанного на JavaScript, ранее не было, потрачено дополнительное время
 - ввиду отсутствия data-test-id атрибутов при написании frontend части приложения, возникли сложности с поиском нужных css-селекторов, которые будут работать в тестах
 - возникли сложности при проверке работы приложения в двух СУБД, MySQL и PostgreSQL

### *Итог по затраченному времени*

- для написания плана тестирования хватило заявленных 10 часов
- при подготовке SUT к запуску и работе возникли сложности по причине отсутствия опыта, в итоге вместо заявленных 10 часов было потрачено 16, основная причина - написание docker-compose и Dockerfile для запуска приложения в контейнере node
- для работы автотестов было написано 8 вспомогательных классов, написание самих автотестов не заняло много времени, так как это самая объемная часть работы, то времени было запланировано с запасом, в итоге я смог уложиться в заявленные 40 часов
- подготовка отчетных документов по итогам тестирования заняла 5 часов, вместо заявленных 8
- подготовка отчетных документов по итогам автоматизации заняла 3 часа, вместо заявленных 5
- дополнительно было потрачено 4 часа на исправление промежуточных правок после промежуточного code review

Итого потрачено на проект - 83 часа