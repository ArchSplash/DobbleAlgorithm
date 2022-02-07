#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define SYMBOLS_AMOUNT 57
#define CARDS_AMOUNT 55
#define SYMBOLS_PER_CARD 8

void import_symbols(const char* filename, char* symbols[]) {
    FILE* file;
    fopen_s(&file, filename, "r");

    if (file == NULL) {
        return;
    }

    int ch, symbol_index = 0, index = 0, buffer = 16, current_buffer = buffer;
    char* symbol = (char*)malloc(sizeof(char) * current_buffer), *tmp;

    if (symbol != NULL) {
        while ((ch = fgetc(file)) != EOF) {
            if ((char) ch == '\n') {
                symbol[index] = '\0';
                symbols[symbol_index++] = symbol;

                index = 0;
                current_buffer = buffer;
                symbol = (char*)malloc(sizeof(char) * current_buffer);
            } else {

                if (index + 1 >= current_buffer) {
                    current_buffer += buffer;
                    tmp = (char *) realloc(symbol, sizeof(char) * current_buffer);
                    if (tmp != NULL) {
                        symbol = tmp;
                    }
                }

                symbol[index++] = (char) ch;
            }
        }
        symbol[index] = '\0';

    }

    fclose(file);
}

void free_symbols(char* symbols[]) {
    for (int i = 0; i < SYMBOLS_AMOUNT; i++) {
        free(symbols[i]);
    }
}

int main() {
    char* symbols[SYMBOLS_AMOUNT] = { 0 };
    import_symbols("Symbols.txt", symbols);

    size_t array_size = SYMBOLS_PER_CARD - 1;
    int cards[CARDS_AMOUNT][SYMBOLS_PER_CARD] = {{0} };
    int card_i = 0;

    // Fill int array
    for (int i = 0; i < SYMBOLS_PER_CARD; i++) {
        int slope = i + 1;

        // Assign to cards[0]
        cards[0][i] = slope;

        for (int x = 0; x < array_size; x++) {
            if (card_i + 1 < CARDS_AMOUNT) {
                cards[++card_i][0] = slope;
                // printf("%d\n", ++card_i);
                int sym_i = 1;
                for (int y = 0; y < array_size; y++) {
                    int value = (int) (SYMBOLS_PER_CARD + array_size * y + ((slope * y + x) % array_size)) + 1;
                    cards[card_i][sym_i++] = value;
                }
            }
        }
    }

   for (int card = 0; card < CARDS_AMOUNT; card++) {
       printf("[%d] -", card + 1);
       for (int i = 0; i < SYMBOLS_PER_CARD; i++) {
           printf(" %s |", symbols[cards[card][i] - 1]);
       }
       printf("\n");
   }

    free_symbols(symbols);
    return 0;
}
