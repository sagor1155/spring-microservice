docker pull mysql/mysql-server:latest

docker run -d \
-p 3306:3306 \
--name mysql-docker-container \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=social-media-database \
-e MYSQL_USER=sagor \
-e MYSQL_PASSWORD=sagor \
mysql/mysql-server:latest
