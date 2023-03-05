# mcdev-gradle-plugin [![Java CI with Gradle][build-badge]][build-link] [![Qodana][qodana-badge]][qodana-link]

🔧 A Gradle plugin for Minecraft server plugin development.

`mcdev` is currently in development.
The following updates may have **_BREAKING CHANGES_** and more supported platforms.

## Usage

To apply the `mcdev` Gradle plugin, use
the [`plugins` block](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) from the Gradle plugins
DSL:

<details open><summary><b>Kotlin DLS</b></summary>
<p>

```gradle
// Replace «platform_id» with the plugin platform.
plugins {
    id("io.github.grassmc.mcdev.«platform_id»") version "0.1.0"
}
```

</p>
</details>

<details><summary><b>Groovy DLS</b></summary>
<p>

```gradle
// Replace «platform_id» with the plugin platform.
plugins {
    id "io.github.grassmc.mcdev.«platform_id»" version "0.1.0"
}
```

</p>
</details>

### Plugin IDs

| Platform   | Platform ID | Plugin ID                          |
|------------|-------------|------------------------------------|
| Spigot     | spigot      | io.github.grassmc.mcdev.spigot     |
| Paper      | paper       | io.github.grassmc.mcdev.paper      |
| Purpur     | purpur      | io.github.grassmc.mcdev.purpur     |
| Velocity   | velocity    | io.github.grassmc.mcdev.velocity   |
| BungeeCord | bungeecord  | io.github.grassmc.mcdev.bungeecord |
| Waterfall  | waterfall   | io.github.grassmc.mcdev.waterfall  |

## Documentation

_Work in progress..._

## License [![license][license-badge]][license-link]

Licensed under the Apache License, Version 2.0 (the "License").

[build-badge]: https://github.com/GrassMC/mcdev-gradle-plugin/actions/workflows/build.yml/badge.svg

[build-link]: https://github.com/GrassMC/mcdev-gradle-plugin/actions/workflows/build.yml

[qodana-badge]: https://github.com/GrassMC/mcdev-gradle-plugin/actions/workflows/code_quality.yml/badge.svg

[qodana-link]: https://github.com/GrassMC/mcdev-gradle-plugin/actions/workflows/code_quality.yml

[license-badge]: https://img.shields.io/github/license/GrassMC/mcdev-gradle-plugin

[license-link]: https://github.com/GrassMC/mcdev-gradle-plugin/blob/main/LICENSE
