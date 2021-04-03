# Conda Virtual Environments

## Create a fresh virtual environment

```code
conda create --name environment_name
```

## Create environment with installed packages

```code
conda create -n environment_name package1 package2 package3
```

For example

```code
conda create -n myenv numpy scipy pandas
```


## Install packages into an existing environment

Make sure that the desired environment is activated.

```code
conda install package_name
```

```code

```

## References

https://docs.conda.io/projects/conda/en/latest/user-guide/tasks/manage-environments.html
