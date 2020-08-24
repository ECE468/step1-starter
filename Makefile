LIB_ANTLR := lib/antlr-4.8-complete.jar
ANTLR_SCRIPT := MicroC.g4

all: compiler

compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java

clean:
	rm -rf classes build
