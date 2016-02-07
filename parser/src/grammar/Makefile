ANTLR_JAR = /usr/local/lib/antlr-4.5-complete.jar
ANTLR = java -jar $(ANTLR_JAR)
ANTLR_OUT_DIR = ../java
PACKAGE = cool
ANTLR_FLAGS = -no-listener -visitor -package $(PACKAGE)
LEXER = CoolLexer
PARSER = CoolParser

%: all

all:
	$(ANTLR) $(ANTLR_FLAGS) -o $(ANTLR_OUT_DIR)/$(PACKAGE)/ -lib $(ANTLR_OUT_DIR)/$(PACKAGE)/ $(PARSER).g4

clean:
	rm -rf ../java/$(PACKAGE)/*.class
	rm -rf ../java/$(PACKAGE)/$(PARSER)*.java
	rm -rf ../java/$(PACKAGE)/$(PARSER)*.tokens
