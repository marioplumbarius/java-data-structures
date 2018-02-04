FROM java:openjdk-8-alpine

# ttf-dejavu is required to render GUI under X11: https://github.com/docker-library/openjdk/issues/73
# tar update so `strip-components` work
# openssl so it work under https
# wget so it does not receive TCP RST
ENV PKGS openssl tar ttf-dejavu wget
RUN apk --update add --no-cache $PKGS

# install intellij
# COPY ./ideaIC-2017.3.4.tar.gz /tmp/idea.tar.gz
RUN wget -O /tmp/idea.tar.gz https://download-cf.jetbrains.com/idea/ideaIC-2017.3.4.tar.gz \
    && mkdir -p /usr/share/intellij \
    && tar -xf /tmp/idea.tar.gz --strip-components=1 -C /usr/share/intellij \
    && rm /tmp/idea.tar.gz