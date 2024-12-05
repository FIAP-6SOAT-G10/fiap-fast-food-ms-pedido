build:
	mvn compile

unit-test:
	mvn test -P unit-test

integration-test:
	mvn test -P integration-test -D CUSTOMER_HOST="a338d2404287143f3854ba9c6c73072c-1301695279.us-east-1.elb.amazonaws.com"

system-test:
	mvn test -P bdd-test

production:
	mvn clean install -DskipTests -Pprd -q
