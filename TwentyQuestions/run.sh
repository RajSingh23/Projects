#!/bin/bash
# Compiles project 4

#!/bin/bash
# Compiles project 4

cd src
javac TwentyQuestions.java
cd ..
rm -r build; mkdir build
mv -v src/*.class build
cd build
clear
java TwentyQuestions
