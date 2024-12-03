FROM openjdk:17-slim

MAINTAINER Grupo 10

ARG awsAccessKeyId
ARG awsSecretAccessKey
ARG awsSessionToken

ENV AWS_ACCESS_KEY_ID=${awsAccessKeyId}
ENV AWS_SECRET_ACCESS_KEY=${awsSecretAccessKey}
ENV AWS_SESSION_TOKEN=${awsSessionToken}

RUN echo $AWS_ACCESS_KEY_ID
RUN echo $AWS_SECRET_ACCESS_KEY
RUN echo $AWS_SESSION_TOKEN

COPY ./target/orders-1.0.jar /usr/bin

WORKDIR /usr/bin
ENTRYPOINT java -jar orders-1.0.jar
EXPOSE 8080