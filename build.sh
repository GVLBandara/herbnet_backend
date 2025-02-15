JAVA_HOME=/usr/lib/jvm/jdk-21.0.3 mvn clean package -DskipTests
docker rmi -f herbnet-backend:dev
docker image build -t herbnet-backend:dev .
