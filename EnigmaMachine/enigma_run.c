#include "enigma.h"

#include <stdio.h>

int main() {
	
	//Initialize enigma
	enigma *enigma_struct = enigma_init();
	add_rotor(enigma_struct, "BDFHJLCPRTXVZNYEIWGAKMUSQO");
	add_rotor(enigma_struct, "AJDKSIRUXBLMWTMCQGZNPYFVOE");
	add_rotor(enigma_struct, "EKMFLGDQVZNTOWYHXUSPAJBRCJ");

	add_reflector(enigma_struct, "YRUHQSLDPXNGOKMIEBFZCWVJAT");

	set_indexes(enigma_struct, "DOG");

	//Get word to be encrypted
	char buff[1000];
	printf("Enter phrase to be encrypted/decrypted: ");
	fscanf(stdin, "%999[^\n]", buff);
	printf("Word to be encrypted/decrypted: %s\n", buff);
	
	//Encrypt word
	encrypt_text(enigma_struct, buff);

	//Free all malloc memory
	free_enigma(enigma_struct);

}