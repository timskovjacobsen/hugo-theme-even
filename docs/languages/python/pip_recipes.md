# pip recipes

## Install package

```code
pip install <package_name>
```

## Upgrade existing package

Upgrade a package to the newest available version:

```code
pip -U <package_name>
```

**Alternatively:**

```code
pip --upgrade <package_name>
```

## Install package in editable mode

Provided: A `setup.py` file must be configured before installing a package in development in editable mode.

```code
pip install -e .
```

**Note:** Remember the period `.` at the end!

This will install the package into site-packages of the active virtual environment and allow it to be edited without having to be reinstalled for changes to take effect.

**Alternatively:**

```code
pip install --editable .
```

## Check info on a specific package

```code
pip show <package_name>
```

Will show package name, version number, summary description, license, etc.

## General help

```code
pip help
```

## Help for certain command

To get help for a certain command, e.g. for inspecting what input parameters a command accepts:

```code
pip <command_name> -h
```

**Alternatively:**

```code
pip --help
```
