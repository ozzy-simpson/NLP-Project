# NLP-Project

## Installation
In order to allow our Java code access to libraries that are not a standard part of Java, we need to download these libraries and make sure that our Java program knows how to find them. We can download these files by pasting the urls below into your browser:
- https://nlp.stanford.edu/software/stanford-corenlp-full-2018-10-05.zip
- https://nlp.stanford.edu/software/stanford-english-corenlp-2018-10-05-models.jar 

It will take several minutes to download these files.

Next, copy both files into the same directory where your Java files are. Extract the contents of the .zip file using the terminal commands (if the first command doesn't work, you can unzip the way you would normally unzip files):

```
unzip stanford-corenlp-full-2018-10-05.zip

cd stanford-corenlp-full-2018-10-05
cp ejml-0.23.jar ..
cp stanford-corenlp-3.9.2.jar ..
cd ..
```

Make sure the .jar files end up copied into the same directory as your Java files.

## Running Program

Before compiling and running the program, change the filter for tweets on [line 37 of Driver.java](Driver.java#L37)

### Mac/Linux
```
javac -classpath ".:./ejml-0.23.jar:./stanford-corenlp-3.9.2.jar:./stanford-english-corenlp-2018-10-05-models.jar" *.java
java -classpath ".:./ejml-0.23.jar:./stanford-corenlp-3.9.2.jar:./stanford-english-corenlp-2018-10-05-models.jar" Driver
```

### Windows
```
javac -classpath ".\stanford-corenlp-3.9.2.jar;.;./ejml-0.23.jar;./stanford-english-corenlp-2018-10-05-models.jar" *.java
java -classpath ".\stanford-corenlp-3.9.2.jar;.;./ejml-0.23.jar;./stanford-english-corenlp-2018-10-05-models.jar" Driver
```
