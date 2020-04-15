JFLAGS = -g -sourcepath src
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  src/Main/Main.java

default: classes

build: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) src/Main/*.class
	$(RM) src/Pieces/*.class
	
run:
	java -cp src/ Main/ Main
