# my-pocket-parser

Clojure app to make a Pocket API call and get stats on the results

This does not authenticate you, only makes the API call

## Installation

- Clone or download the repo
- Create a new app in Pocket in order to use the API
- Open resources/config.edn and fill in the consumer key
- Authenticate yourself with Pocket in order to receive a access token
  - https://getpocket.com/developer/docs/authentication
- Open resources/config.edn and fill in the access token
- run `lein uberjar`

## Usage

    $ java -jar target/uberjar/my-pocket-parser-0.1.0-SNAPSHOT-standalone.jar

## Options

TBD
