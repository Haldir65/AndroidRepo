FROM openjdk:8
LABEL maintainer Álvaro S. <alvaro@alvr.me>

ENV SDK_TOOLS "4333796"
ENV BUILD_TOOLS "27.0.3"
ENV TARGET_SDK "27"
ENV ANDROID_HOME "/opt/sdk"
ENV GLIBC_VERSION "2.27-r0"

# Download and extract Android Tools
RUN wget http://dl.google.com/android/repository/sdk-tools-linux-${SDK_TOOLS}.zip -O /tmp/tools.zip && \
	mkdir -p ${ANDROID_HOME} && \
	unzip /tmp/tools.zip -d ${ANDROID_HOME} && \
	rm -v /tmp/tools.zip

# Install SDK Packages
RUN mkdir -p /root/.android/ && touch /root/.android/repositories.cfg && \
	yes | ${ANDROID_HOME}/tools/bin/sdkmanager "--licenses" && \
	${ANDROID_HOME}/tools/bin/sdkmanager "--update" && \
	${ANDROID_HOME}/tools/bin/sdkmanager "build-tools;${BUILD_TOOLS}" "platform-tools" "platforms;android-${TARGET_SDK}" "extras;android;m2repository" "extras;google;google_play_services" "extras;google;m2repository" "emulator"

RUN mkdir /application
WORKDIR /application
