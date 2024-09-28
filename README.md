# Word Blacklist Plugin for IntelliJ IDEA

> This project is my first small introduction into the world of IDEA Plugin creation. This is currently a WIP.

## Overview

The **Word Blacklist Plugin** for IntelliJ IDEA helps enforce coding standards by preventing the use of specific words 
or phrases within your codebase. By configuring a custom blacklist, the plugin highlights occurrences of blacklisted 
words in code, comments, and documentation, making it easier to maintain consistency and avoid deprecated or unwanted 
terminology.

## Features

- Create a custom list of blacklisted words and phrases.
- Real-time highlighting of blacklisted words in your code.
- Supports classes, methods and fields.
- Easily configurable within IntelliJ IDEA's settings.

## Installation

> For now the plugin isn't published, so this is the only way to install it.

1. Check out the project
2. Run Gradle Build Task

## Usage

1. After installation, go to `File` > `Settings` > `Word Blacklist`.
2. Add your desired words or phrases to the blacklist.
3. The plugin will automatically highlight any occurrences of these words in your project.

## Contribution

Contributions are welcome! Feel free to submit a pull request or report issues.