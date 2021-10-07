# Installing fonts on Ubuntu

Use `wget` to download the zipped font, e.g.

```shell
wget https://download.jetbrains.com/fonts/JetBrainsMono-2.242.zip
```

Unzip the archive

```shell
unzip JetBrainsMono-2.242.zip
```

Move all the font files to the fonts directory for the user

```shell
mv JetBrainsMono-*.tff ~/.local/share/fonts
```

Now the font is installed and can be used e.g. in the terminal or VS Code.

!!! tip "System-wide install"
    ```shell
    sudo mv JetBrainsMono-*.tff /usr/share/fonts/
    ```
