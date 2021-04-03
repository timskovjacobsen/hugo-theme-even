
# Bash and Shell Scripting

## Finding items

To find all files with the extension `.rfa` recursively from current directory:

```bash
find . -type f -name "*.rfa" -print
```

## Bash profile

### WSL (Windows Subsystem for Linux)

The location of the of the `.bashrc` file is located in the home directory

```shell
cd ~/.bashrc
```

## Bash vs zsh

If both are installed, it's very easy to change from one to the other on the same terminal window.

If you are in *bash*, change to *zsh* with

```bash
exec zsh
```

If you are in *bash*, change to *zsh* with

```bash
exec bash
```

### Close a running port

To close port 8000:

```bash
fuser -k -n tcp 8000
```
