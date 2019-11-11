#TODO - find base image with java 8

ENV TRUSTFLAG_HOME=/opt/trustflag-app
ENV TRUSTFLAG_BIN=/opt/trustflag-app/bin

WORKDIR $TRUSTFLAG_HOME

RUN mkdir /app/tmp
ADD target/trustflag-*.jar $TRUSTFLAG_HOME/trustflag-app.jar
ADD entry.sh $TRUSTFLAG_BIN/entry.sh
ADD src/main/resources/application.yml $TRUSTFLAG_HOME/application.yml

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "/opt/trustflag-app/bin/entry.sh"]
