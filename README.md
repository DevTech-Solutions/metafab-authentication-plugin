![logo-noBg](https://user-images.githubusercontent.com/108033858/229975448-d2c11978-57f3-42b9-8db6-6235b94d080c.png)
### Contact Info / Build Requests
* Website: https://devtechsolutions.us
* Join Build Discord: https://discord.gg/e25ydjnWFH
* Twitter: https://twitter.com/ProxLab

---

# Metafab-Authentication-Plugin
Introducing the MetaFab Minecraft Plugin! With this powerful tool, you can seamlessly authenticate your Minecraft players with your MetaFab Ecosystem and Game, creating a more secure and streamlined gaming experience.

One of the standout features of this plugin is its ability to mint and burn assets and currencies directly from the API. This means you can easily manage in-game currencies and items without having to leave the Minecraft client.

But the functionality doesn't stop there - with this plugin, you can also check for assets when players join the game. For example, players can hold rank NFTs in their wallet and receive a corresponding rank in the game upon joining. This allows for a greater level of customization and personalization within your Minecraft server.

Additionally, this plugin offers the ability to redeem NFTs in-game for configured rewards and items. This creates a new level of engagement for players, incentivizing them to collect and trade NFTs to access unique and valuable rewards within the game.

Overall, the MetaFab Minecraft Plugin is a must-have for any Minecraft server looking to elevate their gameplay experience and introduce exciting new features for their players.

---

## Install
1. Navigate to the "releases" section and download the most recent version.

2. To install the plugin, simply drag and drop it into your plugins folder, and then proceed to configure the game-id and ecosystem-id.

Upon redeeming an item, it will be removed from the player's wallet as it is burned. Additionally, you have the option to configure collection items and execute specific commands at the time of redemption.

## Commands
* /metafab - Generate a clickable link for the player to authenticate with metafab.
* /redeem - Opens an inventory containing redeemable items for the player to redeem.

## Building
MetaFab-Authentication-Plugin uses Maven to handle dependencies & building.

#### Requirements
* Java 17 or newer
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
