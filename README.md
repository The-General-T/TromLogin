# TromLogin Plugin

This Minecraft plugin allows you to broadcast customizable welcome and leave messages to your server whenever a player joins or leaves. You can personalize these messages to fit the theme of your server or provide useful information to your players.

> **Note:** This plugin is outdated and may not work with the latest Minecraft versions.

---

## Features
- Broadcasts a welcome message when a player joins the server.
- Broadcasts a leave message when a player leaves the server.
- Fully customizable messages via a configuration file.

## Installation
1. Download the plugin's `.jar` file.
2. Place the file in your server's `plugins` folder.
3. Restart or reload your server.

## Configuration
1. After running the server with the plugin for the first time, a configuration file will be generated in the `plugins/TromLogin` folder.
2. Open the `config.yml` file to customize the messages:

```yaml
##############################################
#                 TromLogin                  #
#              Made by General T.            #
##############################################

# Sets the plugins prefix
prefix: '<red><b>TROMLOGIN</red> <dark_gray>| <reset>'

# Toggles if the message even broadcasts.
broadcastWelcomeMessage: true

# Sets the messages used for the plugin.
# Use {player} for the player's username.
# This plugin only supports MiniMessage formatting: https://docs.advntr.dev/minimessage/format.html

# Example: joinMessage: "<green>Welcome, <rainbow><b>{player}</b></rainbow! Enjoy your time on the server!"
joinMessage: "Welcome, {player}! Enjoy your time on the server!"
leaveMessage: "{player} has left!"
noPermission: "<red>You do not have permission."
reloadPlugin: "<green>Reloaded the plugin!"
```

- `{player}` will be replaced with the player's username.

3. Save the configuration file and reload the plugin or restart the server to apply the changes.

## Usage
- Permissions
  - `tromlogin.broadcast` - Enables the player to see the messages (False by default)
  - `tromlogin.bypass` - The plugin ignores any player with this permission (Great for Admins)

## Known Issues
- As this plugin is outdated, it may not work with newer Minecraft server versions.
- Compatibility issues might occur with other plugins or modifications.

## Contributing
This plugin is no longer actively maintained. However, feel free to fork the project and update it for newer versions of Minecraft. Contributions are welcome!

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

Thank you for using the TromLogin Plugin!

