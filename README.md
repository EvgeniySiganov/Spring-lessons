Перед запуском необходимо запустить PosgreSQL:

docker run --name account-postgres -p 5432:5432 -e POSTGRES_USER=account -e POSTGRES_PASSWORD=account -e POSTGRES_DB=account -d postgres:14

Homework one:
Добавить валидации:

Для всех id - @NotNull при обновлении сущности, @Null при создании.

BankBookDto.number - не пустой

BankBookDto.amount - больше или равен 0

Создать сущность в БД currency в новой схеме dict с полями id (сиквенс из БД) и name (имя валюты, USD и т.д.). Реализовать репозиторий и заменить в CurrencyValidator Set на наш репозиторий и выполнять проверку на наличие значения в БД.

Добавить сущность в БД bank_book для BankBookEntity (все поля должны быть обязательные выполнить валидацию), добавить репозиторий в BankBookService (добавить маппинг для Entity и Dto) и переписать все операции на использование репозитория.