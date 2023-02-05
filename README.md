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


![alt text](https://github.com/Gluk87/ru.javarush.sultangulov.country/blob/dev/img/screen_country.png)

![alt text](https://github.com/Gluk87/ru.javarush.sultangulov.country/blob/dev/img/screen_city.png)
