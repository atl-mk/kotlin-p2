# Kotlin P2

[![Atlassian license](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](LICENSE) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](CONTRIBUTING.md)

A proof of concept that Kotlin can be used in P2 plugin development.

## Usage

This isn't intended to be a boostrap project nor a reference for best-practices, for that see the official documentation on [developer.atlassian.com](https://developer.atlassian.com/server/framework/atlassian-sdk/tutorials-and-guides/). Using Kotlin is not officially supported, and we make no guarantees that there are no problems. From testing we've found that a lot of the main use-cases do work, and that if something doesn't work, it's then possible to fallback to using Java.

## Getting Started

- Start with a product using AMPS by running `atlas-run --product refapp`
- Debug by running `atlas-debug --product refapp`
- For more commands run `atlas-help`

## Tests

Things are ideally integration or E2E tested to verify that things don't just compile, but also work at runtime.

To run all tests, run `atlas-integration-test`

## Security policy

The dependencies versions we've picked are for compatibility only! We recommend you review the versions to ensure they are not vulnerable and match with support product version range. We won't actively upgrade versions, but we'll accept *human* created PRs to match newer appropriate product/platform versions.

## Contributions

Contributions to Kotlin P2 are welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details. 

## License

Copyright (c) 2022 Atlassian and others.
MIT licensed, see [LICENSE](LICENSE) file.

[![With ❤️ from Atlassian](https://raw.githubusercontent.com/atlassian-internal/oss-assets/master/banner-cheers-light.png)](https://www.atlassian.com)
