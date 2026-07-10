FROM jenkins/jenkins:2.568.1-jdk21

USER root

# Install Docker CLI using official repo
RUN apt-get update && \
    apt-get install -y ca-certificates curl gnupg git && \
    install -m 0755 -d /etc/apt/keyrings && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg && \
    chmod a+r /etc/apt/keyrings/docker.gpg && \
    echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null && \
    apt-get update && \
    apt-get install -y docker-ce-cli && \
    rm -rf /var/lib/apt/lists/*

# Fix for dubious ownership in mounted volumes
RUN git config --system --add safe.directory '*'

# Pre-install plugins
RUN jenkins-plugin-cli --plugins "docker-workflow git workflow-aggregator pipeline-stage-view cucumber-reports htmlpublisher"

USER jenkins
