#TODO - find image
FROM openjdk

ENV WILDFLAG_HOME=/opt/wildflag-app
ENV WILDFLAG_BIN=/opt/wildflag-app/bin

WORKDIR $WILDFLAG_HOME

RUN mkdir /app/tmp
ADD target/wildflag-*.jar $WILDFLAG_HOME/wildflag-app.jar
ADD entry.sh $WILDFLAG_BIN/entry.sh
ADD src/main/resources/application.yml $WILDFLAG_HOME/application.yml

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "/opt/wildflag-app/bin/entry.sh"]
