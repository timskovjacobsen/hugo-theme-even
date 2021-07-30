
# Bash and Shell Scripting

## Finding items

To find all files with the extension `.rfa` recursively from current directory:

```bash
find . -type f -name "*.rfa" -print
```

## grep

`grep` (Global Regular Expressions Parser) is a command line program built in to all Unix-like operating systems.

```example

Suppose you have this file, `out.txt`

    ===============================================================================
    Welcome to localhost.run!

    Follow your favourite reverse tunnel at [https://twitter.com/localhost_run].

    **You need a SSH key to access this service.**
    If you get a permission denied follow Gitlab's most excellent howto:
    https://docs.gitlab.com/ee/ssh/
    *Only rsa and ed25519 keys are supported*

    To set up and manage custom domains go to https://admin.localhost.run/

    More details on custom domains (and how to enable subdomains of your custom
    domain) at https://localhost.run/docs/custom-domains

    To explore using localhost.run visit the documentation site:
    https://localhost.run/docs/

    ===============================================================================


    ** your connection id is 17b2574c-454e-401c-913a-77b2220b8313, please mention it if you send me a message about an issue. **

    0c6099b73f7081.localhost.run tunneled with tls termination, https://0c6099b73f7081.localhost.run

You want to find and extract for the last url `https://0c6099b73f7081.localhost.run`. The URL part in the beginning `https://` and the end `localhost.run` are fixed, while the part in between is unknown.

We can extract the URL with `grep` using a regular expression

    cat log.txt | grep -oP 'https://(?!admin|twitter).*.localhost.run'

The regex 'https://.*.localhost.run' will also match the URL with admin and twitter shown in the file, so we have to exclude them from the match.

The `-o` flag denotes that only the match should be printed to stdout rather than the entire line where the match occurs. The `-P` flag specifies to use Perl-compatible regular expressions.

Prints to stdout

    https://0c6099b73f7081.localhost.run

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

### Compare file contents to stdout

```shell
diff some_file.txt <(some_command)
```

### References

* [Process substitution](https://mywiki.wooledge.org/ProcessSubstitution)
