# Docker

## Docker as non-root linux user

On linux, running Docker in the default configuration after install needs to be done as a superuser. That is with `sudo` in front of all docker commands.

Running docker without `sudo` will give the following error:
> docker: Got permission denied while trying to connect to the Docker daemon socket ...

The same problem occurs for docker-compose.

These steps should make Docker run without elevated privileges:

1. Create the Docker group if it does not exist

    ```shell
    sudo groupadd docker
    ```

2. Add user to the Docker group

    ```shell
    sudo usermod -aG docker $USER
    ```

3. (I think this is an attempt to make the changes take effect in the current terminal)

    ```shell
    newgrp docker
    ```

4. Test if it works

    ```shell
    docker run hello-world
    ```

If the changes doesn't take effect, close all terminal windows. If they still don't, try to reboot.

## Kill all containers

```console
sudo docker kill $(docker ps -q)
```

## Commands

* `RUN`
  Images are created in layers. Each `RUN` command corresponds to a layer. It's good practice to keep as few layers as possible, within reason.
  
* `ENTRYPOINT`
  Will always run when the container is created

* `CMD`
  Commands that will run when the container is created unless user overwrites the command
  **TODO: Check if this is correct!**

* `COPY`
    Copies files from the host to the Docker containers. Only files can be copied.

* `ADD`
    Can take files, URLs or archives. Adding archives will auto-unpack them into the container.

    > **Note:** Use `.dockerignore` to avoid unwanted files and directories being copied or added into the image.
