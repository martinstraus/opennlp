#!/bin/bash

src=./src/main/models
lang=es

opennlp DoccatTrainer -model ${src}/es-intent.bin -lang ${lang} -data ${src}/es-intent.train -encoding UTF-8
opennlp POSTaggerTrainer -model ${src}/es-tagger.bin -lang ${lang} -data ${src}/es-tagger.train -dict ${src}/symbols.xml -encoding UTF-8