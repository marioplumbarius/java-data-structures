FROM openjdk:8-jdk-alpine

# ttf-dejavu is required to render GUI under X11: https://github.com/docker-library/openjdk/issues/73
RUN apk --update add --no-cache ttf-dejavu

# install intellij
RUN wget -O /tmp/idea.tar.gz https://download-cf.jetbrains.com/idea/ideaIC-2017.3.4.tar.gz \
    && mkdir -p /usr/share/intellij \
    && tar -xf /tmp/idea.tar.gz --strip-components=1 -C /usr/share/intellij \
    && rm /tmp/idea.tar.gz
