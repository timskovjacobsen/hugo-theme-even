
# Git Recipes

This is a collection of recipes for tackling common scenarios while working with Git.

If you think a certain recipe should be added, you can either add it yourself or
create an issue and wait for another to add it.

## Introduction

Each recipe has a headline and a more detailed description of the scenario that the
recipe solves.

The recipes assume the reader is familiar with basic Git nomenclature, but just need
some best practices or pointers to combine the knowledge of the features of Git into
a certain desired action.

**TODO:** Move paragraph below to a contributing (or some other) page
While not necessary, it's encouraged to include a diagram in all recipes describing
the operations taken during the recipe. A great tool for making such diagrams in
`draw.io <https://www.draw.io/>`_.
There's also a `desktop version <https://github.com/jgraph/drawio-desktop/releases>`_.

## Initializing a Repository

## Add a Remote Repository

```shell
git remote add origin <url>
```

To verify is the local repository has a remote

```shell
git remote -v
```

## Push to a Remote

```shell
git push -u origin <remote>
```

## Ignoring files

It is often not desired to have all files in a project under version control.

To make Git ignores files create a ``.gitignore`` file and list the files that should
be ignored inside it.

```python
# Ignore all png files
*.pngdocs

# But do not ignore this specific png file
!import_image.png
```

## Unstage files

```shell
git reset <commit> <path>
```

Providing no arguments will unstage all files form the index.

## Create a branch

**Scenario:** > Describe scenario

## Merge a branch into another

**Scenario:** > Describe scenario

## Resolve merge conflict

**Scenario:** > Describe scenario

## Delete a branch

```shell
git branch -d branch_name
```

## Go back to a previous commit

**Scenario:** > You want to go back in history to a previous commit (snapshot) of the
repository. The snapshot you want to go back to has the commit hash (ID) `083bb3d`.  

Check out this exact commit by

```shell
git checkout 083bb3d
```

From here you can look around to see what the state of the repo is for this particular
commit.

.. Note::
    This will leave you in a so-called **detached HEAD** state. It's not as bad
    as it sounds. It just means that what is currently checked out is not a local branch.
    Thus, potential new commits will not get assigned to a local branch.

If you want to take up work from here and create new commits, you can **create a new
branch** which has commit `083bb3d` as its point of origin:

```shell
git branch <new_branch_name>
git checkout <new_branch_name>
```

You now have a brand new branch to continue work.

**TODO:** Flow diagram

## Git Log

Simple log with each commit at a glance in a single line

```shell
git log --oneline
```

Show only the last 5 commits

```shell
git log --oneline -5
```

Show a (directed acyclic) graph of the history in the terminal

```shell
git log --oneline --graph
```

## Create a tag

**Reference:** https://git-scm.com/book/en/v2/Git-Basics-Tagging

### Annotated tags

```shell
git tag -a version_no -m "<tagging message>"
```

E.g.

```shell
git tag -a v0.1.0 -m "Version v0.1.0"
```

### Lightweight tags

### Tagging previous commits

## Pulling a remote branch into a local branch

There are multiple ways to pull the changes from the remote _master_ branch into a local branch _local-branch_.

The way that is arguably simplest to understand is

```shell
git checkout master
git pull
git checkout <local-branch>
git merge master
```

Possible variations are

```shell
git fetch
git merge origin/master
```

```shell
git merge origin master
```

```shell
git fetch
git rebase origin/master
```

```shell
git rebase origin master
```

## Scripting in Git

ref: https://stackoverflow.com/questions/5188320/how-can-i-get-a-list-of-git-branches-ordered-by-most-recent-commit

```shell
git for-each-ref --sort=committerdate refs/heads/ --format='%(HEAD) %(color:yellow)%(refname:short)%(color:reset) - %(color:red)%(objectname:short)%(color:reset) - %(contents:subject) - %(authorname) (%(color:green)%(committerdate:relative)%(color:reset))'
```

.. TODO: amend to a commit (git commit --amend). 
.. TODO: untrack a file (git -r --cached <file_path>)
