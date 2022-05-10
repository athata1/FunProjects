CC=gcc
DEPS=hello.h
CFLAGS=-Wall -Werror
DEPS= enigma.h


%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

enigma: enigma.o enigma_run.o
	$(CC) -o enigma enigma.o enigma_run.o $(CFLAGS)
