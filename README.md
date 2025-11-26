# NetworkPlayerLimiter

**NetworkPlayerLimiter** is a lightweight Velocity proxy plugin designed to manage and restrict player connection limits on your network. It allows administrators to set specific connection limits per player, manage bypasses via permissions, and reload configurations on the fly.

## Features

- **Permission based bypass:** Players with networkplayerlimiter.bypass will be able to bypass limit.
- **Configurable:** Customize kick message, set whether ping response should be modified.
- **Change limit on fly:** Through command or file.

## Installation

1. Download `NetworkPlayerLimiter.jar`.
2. Place the jar file into your Velocity `plugins/` folder.
3. Restart your Velocity proxy.

## Commands

All commands are prefixed with `/npl`.

| Command                  | Permission                   | Description                     |
|:-------------------------|:-----------------------------|:--------------------------------|
| `/npl setLimit <amount>` | `networkplayerlimiter.admin` | Sets player limit.              |
| `/npl reload`            | `networkplayerlimiter.admin` | Reloads the configuration file. |

## Permissions

| Permission Node               | Description                                                |
|:------------------------------|:-----------------------------------------------------------|
| `networkplayerlimiter.bypass` | Players with this permission ignore all connection limits. |
| `networkplayerlimiter.admin`  | Grants access to `/npl` administrative commands.           |