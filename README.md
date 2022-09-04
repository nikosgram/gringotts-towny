# gringotts-towny

Gringotts-Towny allows the creation of [Gringotts](https://github.com/nikosgram/gringotts) vaults and account
for [Towny](https://www.spigotmc.org/resources/towny-advanced.72694/)'s nations and towns.

## Configuration

```yaml
# supported languages: "custom" (default, english), "de" (german), and "pt-BR" (brazilian portuguese).
language: custom

# changes the required sign name for the creation of town/nation vaults
# Example: [town vault]
town_sign_vault_name: 'town'
nation_sign_vault_name: 'nation'
```

## Permissions

`gringotts.createvault.town`
---
Gives the necessary permissions to the players to create vault for their towns.

`gringotts.createvault.nation`
---
Gives the necessary permissions to the players to create vault for their nations.


Development
-----------
Would you like to make changes to Gringotts Towny yourself? Fork it!
Pull requests are very welcome, but please make sure your changes fulfill the Gringotts Towny quality baseline:

* new features, settings, permissions are documented
* required dependencies are all added to the build by Maven, not included in the repo
* the project builds with Maven out-of-the-box

Gringotts uses the [Maven 3](http://maven.apache.org/) build system. Build a working plugin jar with the command

```shell
mvn compile install
```

License
-------
All code within Gringotts is licensed under the BSD 2-clause license. See `LICENSE` for details.
