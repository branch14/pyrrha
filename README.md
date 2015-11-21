# pyrrha

Pyrrha will listen to a queue named `pyrrha` on RabbitMQ and transform the
messages received into XSLX files, yes -- XSLX files.

Currently it assumes that the message is encoded in JSON. The JSON
should be a list of rows, while the list of rows should be lists of
cells.

## Example

The following JSON

    [ [ 1, 2 ], [ 3, 4 ] ]

will be converted to the follwing spread sheet

    ---------
    | 1 | 2 |
    ---------
    | 3 | 4 |
    ---------

## Usage

For now start it with...

    lein run

## License

Copyright © 2015 Phil Hofmann

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
