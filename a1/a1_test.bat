rem A1 batch test
echo A1 batch test
echo username:%USERNAME%
echo CD

cd stats-mvn
call mvn clean -q
call mvn package -q
call java -jar target/stats-mvn.jar 1 10000 1
call mvn clean -q
cd ..

start "wildfly" cmd /k standalone
sleep 20

cd stats-app
call mvn clean install -q
call mvn wildfly:undeploy -q
call mvn wildfly:deploy  -q
call curl http://127.0.0.1:8080/stats-web/add-data?value=10
call curl http://127.0.0.1:8080/stats-web/insert-data?value=10
call curl http://127.0.0.1:8080/stats-web/get?value=count
cd ..

cd stats-client
call mvn clean package -q
call java -jar target/stats-client.jar
cd ..

cd stats-app
call mvn wildfly:undeploy -q
call mvn clean -q
cd ..

taskkill /FI "WindowTitle eq wildfly*"  /T /F >null

echo END