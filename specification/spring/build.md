```bash
# build commond
$  ./mvnw clean package -DskipTests=true
# remove snapshot
$ rm target/stock-crawler
# run jar
$ java -jar target/stock-crawler.jar --spring.profiles.active=local

# test function
$ mvn function:run -Dspring-boot.run.profiles=local
# call function
$ curl localhost:8080 -H "Content-Type: application/json" -d '{"data":"aGVsbG8="}'
```