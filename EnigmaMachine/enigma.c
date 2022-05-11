#include "enigma.h"

#include <assert.h>
#include <malloc.h>
#include <stdio.h>
#include <string.h>

static char encrypt_char(enigma *, char );
static void increment_rotors(enigma *);

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
	enigma_struct->rotors[num_rotors][strlen(rotor)] = '\0';

	enigma_struct->indexes = realloc(enigma_struct->indexes, sizeof(int) * 
								     (num_rotors + 1));
	enigma_struct->indexes[num_rotors] = 0;
	
	enigma_struct->curr_ring_setting = realloc(enigma_struct->curr_ring_setting, 
											   sizeof(int) * (num_rotors + 1));
	enigma_struct->curr_ring_setting[num_rotors] = 0;
	enigma_struct->num_rotors++;

}

void set_ring_setting(enigma *enigma_struct,int rotor_index, char letter) {
	assert(enigma_struct != NULL);
	assert(rotor_index < enigma_struct->num_rotors);
	int isValid = (letter >= 'A' && letter <= 'Z') ? 1 : 0;
	assert(isValid == 1);

	int prev_shift = enigma_struct->curr_ring_setting[rotor_index];
	int new_shift = letter - 'A';
	printf("%d %d\n", prev_shift, new_shift);
	printf("%s\n", enigma_struct->rotors[rotor_index]);
	for (int i = 0; i < 26; i++) {
		char new_char = (enigma_struct->rotors[rotor_index][i] - 'A' - 
						prev_shift + new_shift) % 26 + 'A';
		enigma_struct->rotors[rotor_index][i] = new_char; 
	}
	enigma_struct->curr_ring_setting[rotor_index] = new_shift;

}
void set_indexes(enigma *enigma_struct, char *indexes) {
	assert(enigma_struct != NULL);
	assert(indexes != NULL);

	assert(enigma_struct->num_rotors == strlen(indexes));
	

	int num_rotors = enigma_struct->num_rotors;
	for (int i = 0; i < num_rotors; i++) {
		int a = (indexes[i] >= 'A' && (indexes[i] <= 'Z')) ? 1: 0;
		assert(a == 1);
		enigma_struct->indexes[num_rotors - i - 1] = indexes[i] - 'A';
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

char *encrypt_text(enigma *enigma_struct, char *text) {
	assert(enigma_struct != NULL);
	assert(text != NULL);

	char *output = malloc(strlen(text) + 1);

	int len = strlen(text);
	for (int i = 0; i < len; i++) {
		if (text[i] >= 'A' && (text[i] <= 'Z')) {
			output[i] = encrypt_char(enigma_struct, text[i]);
		}
		else {
			output[i] = text[i];
		}
	}
	return output;
}

static char encrypt_char(enigma *enigma_struct, char letter) {
	assert(enigma_struct != NULL);
	increment_rotors(enigma_struct);

	//Iterate through rotors
	int curr_index = (enigma_struct->indexes[0] + letter - 'A') % 26;	
	int len = enigma_struct->num_rotors;
	for (int i = 0; i < len - 1; i++) {
		char curr_char = enigma_struct->rotors[i][curr_index];
		curr_index = (curr_char - 'A' - enigma_struct->indexes[i] + 26
					 + enigma_struct->indexes[i + 1]) % 26;
	}
	char curr_char = enigma_struct->rotors[len - 1][curr_index];
	curr_index = (curr_char - 'A' - enigma_struct->indexes[len - 1] + 26) % 26;

	//Go through reflector
	curr_index = (enigma_struct->reflector[curr_index] - 'A' +
				 enigma_struct->indexes[len - 1]) % 26;
	
	//Go through rotors backwards
	for (int i = len - 1; i >= 1; i--) {
		int index = -1;
		for (int j = 0; j < 26; j++) {
			if (enigma_struct->rotors[i][j] == curr_index + 'A') {
				index = j;
				break;
			}
		}
		assert(index != -1);
		curr_index = (26 + index - enigma_struct->indexes[i] 
					 + enigma_struct->indexes[i - 1]) % 26;
	}
	int index = -1;
	for (int i = 0; i < 26; i++) {
		if (enigma_struct->rotors[0][i] == curr_index + 'A') {
			index = i;
			break;
		}
	}
	assert(index != -1);
	curr_index = (26 + index - enigma_struct->indexes[0]) % 26;
	return curr_index + 'A';
}

static void increment_rotors(enigma *enigma_struct) {
	assert(enigma_struct != NULL);
	int num_rotors = enigma_struct->num_rotors;
	enigma_struct->indexes[0] = (enigma_struct->indexes[0] + 1) % 26;
	for (int i = 0; i < num_rotors - 1; i++) {
		if (enigma_struct->indexes[i] == 0) {
			enigma_struct->indexes[i + 1] = (enigma_struct->indexes[i+1] + 1) % 26;
		}
		else {
			break;
		}
	}
}
