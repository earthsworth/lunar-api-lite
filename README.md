# lunar-api-lite

## Features

We're not going to implement all the features, this servlet is designed to run on the local

If you are looking for the full implementation, please visit [this repo](https://github.com/earthsworth/lunar-api)

- [x] Use cosmetics
- [ ] Save cosmetics settings
- [ ] Use emotes
- [ ] Save emote settings
- [ ] Game Metadata
- [ ] Subscription service
- [ ] Federation

These features are not designed to be implemented

- [ ] Social system

## Usage

- Install Java 21 (Must use a jdk)
- Download Celestial launcher
- Download lunar-api-lite
- Install BrowserDebugger agent
- Execute `java -jar lunar-api-lite.jar`
- Set the VM parameters for your game

```text
-DserviceOverrideAuthenticator=ws://127.0.0.1:8080/ws
-DserviceOverrideAssetServer=ws://127.0.0.1:8080/ws
```

## License

This work is licensed under GPL-3.0

You're allowed to use, share and modify.
