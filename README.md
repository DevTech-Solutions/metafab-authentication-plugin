![logo-noBg](https://user-images.githubusercontent.com/108033858/229975448-d2c11978-57f3-42b9-8db6-6235b94d080c.png)
### Contact Info / Build Requests
* Website: https://devtechsolutions.us
* Join Build Discord: https://discord.gg/e25ydjnWFH
* Twitter: https://twitter.com/ProxLab

---

# Metafab-Authentication-Plugin
Authenticate Minecraft Players with your MetaFab Ecosystem & Game.

#### Install
1. Navigate to the "releases" section and download the most recent version.

2. To install the plugin, simply drag and drop it into your plugins folder, and then proceed to configure the game-id and ecosystem-id.

Upon redeeming an item, it will be removed from the player's wallet as it is burned. Additionally, you have the option to configure collection items and execute specific commands at the time of redemption.

## Commands
* /metafab - Generate a clickable link for the player to authenticate with metafab.
* /redeem - Opens an inventory containing redeemable items for the player to redeem.

## Building
MetaFab-Authentication-Plugin uses Maven to handle dependencies & building.

#### Requirements
* Java 17 JDK or newer
* Git

#### Compiling from source
```sh
git clone https://github.com/DevTech-Solutions/metafab-authentication-plugin.git
cd metafab-authentication-plugin/
mvn clean install
```

## Contributing
#### Pull Requests
In case you enhance or modify the plugin in a way that could benefit others, we kindly request that you contemplate submitting a pull request to integrate your modifications into the upstream project, particularly if they address any bugs.

MetaFab-Authentication-Plugin loosely follows the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). Generally, try to copy the style of code found in the class you're editing.

## License
MetaFab-Authentication-Plugin is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/DevTech-Solutions/metafab-authentication-plugin/blob/main/LICENSE) for more info.