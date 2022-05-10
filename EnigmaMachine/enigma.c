#include "enigma.h"

#include <assert.h>
#include <malloc.h>
#include <stdio.h>
#include <string.h>

enigma *enigma_init() {
	enigma *enigma_struct = malloc(sizeof(enigma));
	enigma_struct->num_rotors = 0;
	enigma_struct->rotors = NULL;
	enigma_struct->reflector = NULL;
	enigma_struct->indexes = NULL;
	return enigma_struct;
}

void add_rotor(enigma *enigma_struct, char *rotor) {
	assert(strlen(rotor) == 26);
	assert(enigma_struct != NULL);

	int num_rotors = enigma_struct->num_rotors;
	
	enigma_struct->rotors = realloc(enigma_struct->rotors, 
							(num_rotors + 1) *
							(sizeof(*(enigma_struct->rotors))));
	
	enigma_struct->rotors[num_rotors] = malloc(sizeof(char) * 27);
	strncpy(enigma_struct->rotors[num_rotors], rotor, strlen(rotor));
	enigma_struct->rotors[num_rotors][strlen(rotor)] = '\n';

	enigma_struct->indexes = realloc(enigma_struct->indexes, sizeof(int) * 
								     (num_rotors + 1));
	enigma_struct->indexes[num_rotors] = 0;
	enigma_struct->num_rotors++;

}

void set_indexes(enigma *enigma_struct, char *indexes) {
	assert(enigma_struct != NULL);
	assert(indexes != NULL);

	assert(enigma_struct->num_rotors == strlen(indexes));
	

	int num_rotors = enigma_struct->num_rotors;
	for (int i = 0; i < num_rotors; i++) {
		int a = (indexes[i] >= 'A' && (indexes[i] <= 'Z')) ? 1: 0;
		assert(a == 1);
		enigma_struct->indexes[i] = indexes[i] - 'A';
	}
}

void add_reflector(enigma *enigma_struct, char *reflector) {
	assert(enigma_struct != NULL);
	assert(reflector != NULL);
	assert(strlen(reflector) == 26);

	enigma_struct->reflector = malloc(sizeof(char) * 27);
	strncpy(enigma_struct->reflector, reflector, strlen(reflector));
	enigma_struct->reflector[strlen(reflector)] = '\0';

	return;
}

void free_enigma(enigma *enigma_struct) {
	
	int num_roters = enigma_struct->num_rotors;
	for (int i = 0; i < num_roters; i++) {
		free(enigma_struct->rotors[i]);
	}
	free(enigma_struct->rotors);
	free(enigma_struct->indexes);
	free(enigma_struct->reflector);
	free(enigma_struct);
}
