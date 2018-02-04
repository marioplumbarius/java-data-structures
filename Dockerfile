FROM kdelfour/cloud9-docker

# sh becomes bash
RUN ln -sf /bin/bash /bin/sh

# gradle
RUN apt-get update && apt-get install -y --no-install-recommends unzip zip \
  && curl -s https://get.sdkman.io | bash \
  && source /root/.sdkman/bin/sdkman-init.sh \
  && sdk install gradle

# java-8
RUN apt-get install -y --no-install-recommends software-properties-common python-software-properties \
  && add-apt-repository ppa:openjdk-r/ppa -y \
  && apt-get update -y \
  && apt-get install openjdk-8-jdk -y --no-install-recommends \
  && update-alternatives --config java \
  && update-alternatives --config javac \
  && java -version