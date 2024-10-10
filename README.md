<div align="center">
<p>
    <img width="400" src="https://raw.githubusercontent.com/Dlindustries/database/main/Chaevly/chaevly.svg">
</p>

[website](https://github.com/DLindustries/Chaevly) |
[YouTube](https://youtube.com/@quoly)

</div>

Chaevly is a free and open-source mixin-based injection hacked client using the Fabric API for Minecraft.

## Issues

If you notice any bugs or missing features, you can let us know by opening an
issue [here](https://github.com/DLindustries/Chaevly/issues).

## License

This project is subject to the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html). This
does only apply for source code located directly in this clean repository. During the development and compilation
process, additional source code may be used to which we have obtained no rights. Such code is not covered by the GPL
license.

For those who are unfamiliar with the license, here is a summary of its main points. This is by no means legal advice
nor legally binding.

*Actions that you are allowed to do:*

- Use
- Share
- Modify

*If you do decide to use ANY code from the source:*

- **You must disclose the source code of your modified work and the source code you took from this project. This means
  you are not allowed to use code from this project (even partially) in a closed-source (or even obfuscated)
  application.**
- **Your modified application must also be licensed under the GPL**

## Setting up a Workspace

Chaevly uses Gradle, to make sure that it is installed properly you can
check [Gradle's website](https://gradle.org/install/).

1. Clone the repository using `git clone --recurse-submodules https://github.com/DLindustries/Chaevly`.
2. CD into the local repository.
3. Run `./gradlew genSources`.
4. Open the folder as a Gradle project in your preferred IDE.
5. Run the client.

## Additional libraries

### Mixins

Mixins can be used to modify classes at runtime before they are loaded. Chaevly uses it to inject its code into the
Minecraft client. This way, none of Mojang's copyrighted code is shipped. If you want to learn more about it, check out
its [Documentation](https://docs.spongepowered.org/5.1.0/en/plugin/internals/mixins.html).

## Contributing

We appreciate contributions. So if you want to support us, feel free to make changes to Chaevly's source code and
submit a pull request.

## Stats

![Alt](https://repobeats.axiom.co/api/embed/63b90c11eafcb6a769c328d8a7f9afb5556dd3ca.svg "Repobeats analytics image")
