# Jenkins

## Running Jenkins in Docker

```shell
docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
```

* The `8080` port stems from Jenkins running on Tomcat (a Java service).
* The `50000` is the master/slave communication
* The `-d` flag means we run the container in the background (detached mode)
* With the `-v jenkins_home:/var/jenkins_home` we bind a names volume, giving the `/var/jenkins_home` folder an alias of `jenkins_home`. The volume is needed because we want to have *data persistance* so when we restart the Jenkins instance, we will retain the data from previous runs. The data we want to retain is configuration, users, plugins and builds.
* `lts` denotes that we want the latest image

### References

* [Image from Docker Hub](https://hub.docker.com/r/jenkins/jenkins)
* [Docker image docs](https://github.com/jenkinsci/docker/blob/master/README.md)

## Connect Jenkins and Gitea with Docker

Configure Gitea Server:

* Name: Any human friendly name
* Server URL: `http://172.17.0.1:3000`

At least the server URL was that in this specific test case. The base url `http://172.17.0.1` can be seen from the terminal where the Gitea instance was started with Docker.

### Gitea test instance

inside the docker container, the gitea instance for testing has a jenkins user `jenkins`. It has an access token of `f7f00084dc7e511c15770faf5f1a18b13923fe36` with name `jenkins-ci`.

### References

* [Jenkins Gitea plugin demo](https://www.youtube.com/watch?v=B4boQozMaRA)

## Local setup and installation

!!! tip "Run in Docker instead"
    It's often preferred to run Jenkins in a Docker container instead of through a local installation.

Get initial password (Windows)

```console
cat C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\secrets\initialAdminPassword
```

!!! warning "Admin rights required"
    The terminal must be opened with admin rights
