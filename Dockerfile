FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
RUN yum install -y shadow-utils
ARG USERNAME=app
ARG GROUPNAME=app
ARG UID=1000
ARG GID=1000

RUN groupadd -g $GID $GROUPNAME && \
    useradd -m -s /bin/bash -u $UID -g $GID $USERNAME
RUN chown -R $USERNAME:$GROUPNAME /tmp

USER $USERNAME
VOLUME [ "/tmp" ]
WORKDIR /home/$USERNAME/

COPY target/sample-schedulelaunch-0.1.0-SNAPSHOT.jar /home/$USERNAME/
ENTRYPOINT [ "java" ]
CMD [ "-jar", "sample-schedulelaunch-0.1.0-SNAPSHOT.jar" ]
