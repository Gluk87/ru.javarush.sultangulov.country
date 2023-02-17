# Project: SQL, JDBC, Hibernate (and some Spring)

Instruction:
1) Start Database MySQL on Docker:
   docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8
2) Upload a dump with scripts to the Database;
3) Start Redis:
   docker run -d --name redis -p 6379:6379 redis:latest
4) Launch the Application. On startup, it checks the performance of the Database and the Redis:

![alt text](https://github.com/Gluk87/ru.javarush.sultangulov.country/blob/dev/img/screen_redis.png)

5) Launch client for REST requests (e.g. Insomnia)

Request examples (POST):

http://localhost:8080/country/all
{	
	"maxItems": 10
}


http://localhost:8080/city/all
{	
	"maxItems": 10,
	"offset": 10
}

http://localhost:8080/city/count

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