#ifndef ENIGMA
#define ENIGMA

//Typedefs
typedef struct enigma {
	int num_rotors;
	char **rotors;
	char *reflector;
	int *indexes;
	int *curr_ring_setting;
} enigma;

//method prototypes
enigma *enigma_init();
void add_rotor(enigma *, char *);
void set_indexes(enigma *, char *);
void add_reflector(enigma *, char *);
void free_enigma(enigma *);
char *encrypt_text(enigma *, char *);
void set_ring_setting(enigma *, int, char);

#endif
