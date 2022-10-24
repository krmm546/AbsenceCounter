FROM openjdk:17-jdk-buster

WORKDIR /build

COPY ./ ./

RUN apt update
RUN apt install -y wget unzip
RUN wget https://services.gradle.org/distributions/gradle-7.5.1-bin.zip
RUN mkdir /opt/gradle
RUN unzip -d /opt/gradle gradle-7.5.1-bin.zip
RUN export PATH=$PATH:/opt/gradle/gradle-7.5.1/bin/
RUN echo 'export PATH=$PATH:/opt/gradle/gradle-7.5.1/bin/' >> /root/.bashrc
RUN apt install -y npm
RUN npm install -g typescript
RUN npm install -g sass
RUN cd /build/src/main/resources/public/static && sass .
RUN cd /build/src/main/resources/public/static/scripts/ && tsc
RUN cd /build && /opt/gradle/gradle-7.5.1/bin//gradle shadowJar
