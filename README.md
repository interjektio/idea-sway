Rudimentary Sway support for IntelliJ IDEA Ultimate
===================================================

This plugin adds IntelliJ IDEA support for the [Sway](https://docs.fuel.network/docs/sway/) smart contract language.

It's very rudimentary. Behind the scenes, it uses `forc-lsp` as the language server, but it doesn't provide many
features out of the box yet. Syntax hilighting is also very basic and mostly written by ChatGPT.

## Installation

1. Follow the steps here to install the Fuel toolchain: https://docs.fuel.network/guides/contract-quickstart/
2. Ensure you have the `forc-lsp` command and it's in PATH.
3. Clone this repo
4. Build the project using `./gradlew buildPlugin`
5. Install the plugin by going to `File -> Settings -> Plugins -> Install Plugin from Disk...` in
   IntelliJ IDEA Ultimate and selecting the `.zip` file in `build/distributions`.

To develop, you can use `./gradlew runIde` to run a new IntelliJ IDEA instance with the plugin installed.

## TODO

- Enable configuring location of `forc-lsp`
- Generate the lexer properly from this: https://github.com/FuelLabs/sway-vscode-plugin/blob/master/syntaxes/sway.tmLanguage.json
- Improve hilighting 
- Autocompletion/hover/etc (this probably needs development from `forc-lsp` as it's not very good yet)
