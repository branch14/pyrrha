# pyrrha

Pyrrha will listen to a queue named `pyrrha` on RabbitMQ and transform
the messages received into XSLX files, yes -- XSLX files. The result
will be published to an exchange called `pyrrha`.

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

## Making use of...

* https://github.com/mjul/docjure
* http://reference.clojurerabbitmq.info/



## License

Copyright © 2015 Phil Hofmann

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

### Trivia

In Greek mythology, Pyrrha (/ˈpɪrə/; Greek: Πύρρα) was the daughter of
Epimetheus and Pandora and wife of Deucalion.

When Zeus decided to end the Bronze Age with the great deluge,
Deucalion and his wife, Pyrrha, were the only survivors. Even though
he was imprisoned, Prometheus who could see the future and had
foreseen the coming of this flood told his son, Deucalion, to build an
ark and, thus, they survived. During the flood, they landed on Mount
Parnassus, the only place spared by the flood.

Once the deluge was over and the couple were on land again, Deucalion
consulted an oracle of Themis about how to repopulate the earth. He
was told to throw the bones of his mother behind his
shoulder. Deucalion and Pyrrha understood the "mother" to be Gaia, the
mother of all living things, and the "bones" to be rocks. They threw
the rocks behind their shoulders, which soon began to lose their
hardness and change form. Their mass grew greater, and the beginnings
of human form emerged. The parts that were soft and moist became skin,
the veins of the rock became people's veins, and the hardest parts of
the rocks became bones. The stones thrown by Pyrrha became women;
those thrown by Deucalion became men.

Deucalion and Pyrrha had three sons, Hellen, Amphictyon, Orestheus;
and three daughters Protogeneia, Pandora II and Thyia. (Source:
[Wikipedia](https://en.wikipedia.org/wiki/Pyrrha))
