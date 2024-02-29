# my-pocket-parser

Clojure app to make a Pocket API call and get stats on the results

This does not authenticate you, only makes the API call

## Installation

- Clone or download the repo
- Create a new app in Pocket in order to use the API
- Authenticate yourself with Pocket in order to receive an access token
  - https://getpocket.com/developer/docs/authentication
- Open resources/config.edn and fill in the consumer key and access token

## Usage

    To compile & execute:
    $ ./run.sh
    To build uberjar:
    $ ./build.sh
    To execute the uberjar:
    $ java -jar target/my_pocket_parser-(version).jar
    To build a Native image (tested with GraalVM 21.0.2):
    $ ./native-image.sh
    Note! ensure that the resources/config.edn is always in the same file/folder layout with the executable else it can't find it.

## Options

None
