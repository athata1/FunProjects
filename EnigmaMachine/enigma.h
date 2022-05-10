#ifndef ENIGMA
#define ENIGMA

//Typedefs
typedef struct enigma {
	int num_rotors;
	char **rotors;
	char *reflector;
	int *indexes;
} enigma;

//method prototypes
enigma *enigma_init();
void add_rotor(enigma *, char *);
void set_indexes(enigma *, char *);
void add_reflector(enigma *, char *);
void free_enigma(enigma *);

#endif
