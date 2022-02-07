CARDS_AMOUNT = 55
SYMBOLS_PER_CARD = 8


def import_symbols(file: str) -> list:
    with open(file) as f:
        return f.read().splitlines()


def main():
    # Assuming there are always exactly 57 symbols
    symbols = import_symbols('Symbols.txt')

    array_size = SYMBOLS_PER_CARD - 1
    cards = [[]]
    for slope in range(1, SYMBOLS_PER_CARD + 1):
        cards[0].append(slope)

        for x in range(array_size):
            card = [slope]

            for y in range(array_size):
                index = (SYMBOLS_PER_CARD + array_size * y + ((slope * y + x) % array_size)) + 1
                card.append(index)

            cards.append(card)

    for pos, card in enumerate(cards):
        print("[" + str(pos + 1) + "] -", end='')
        for symbol in card:
            print(" " + symbols[symbol - 1] + " |", end='')
        print()


if __name__ == '__main__':
    main()
