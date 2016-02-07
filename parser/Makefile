MAKE = make
%: build

build:
	$(MAKE) -C ./src/grammar -f Makefile
	$(MAKE) -C ./src/java -f Makefile

clean:
	$(MAKE) -C ./src/grammar -f Makefile clean
	$(MAKE) -C ./src/java -f Makefile clean
