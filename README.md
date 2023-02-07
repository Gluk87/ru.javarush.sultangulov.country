# Проект по теме: SQL, JDBC, Hibernate (и немного Spring)

Инструкция:
1) Запустить на Docker БД MySQL;
2) Загрузить в БД дамп со скриптами;
3) Запустить клиента для REST-запросов

Примеры запросов (POST):

http://localhost:8080/countries
{	
	"maxItems": 10
}


http://localhost:8080/cities
{	
	"maxItems": 10,
	"offset": 10
}

http://localhost:8080/count

http://localhost:8080/city
{
"id": 10
}

{
"id": 99999
}

![alt text](https://github.com/Gluk87/ru.javarush.sultangulov.country/blob/dev/img/screen_country.png)

![alt text](https://github.com/Gluk87/ru.javarush.sultangulov.country/blob/dev/img/screen_city.png)

![alt text](https://github.com/Gluk87/ru.javarush.sultangulov.country/blob/dev/img/screen_count.png)

![alt text](https://github.com/Gluk87/ru.javarush.sultangulov.country/blob/dev/img/screen_error.png)
