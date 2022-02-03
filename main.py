MAX_SYMBOLS = 57
MAX_CARDS = 55
SYMBOLS_PER_CARD = 8


def import_symbols(file: str) -> list:
    with open(file) as f:
        return f.read().splitlines()


def main():
    # Assuming there are always exactly 57 symbols
    symbols = import_symbols('Symbols.txt')

    if len(symbols) < MAX_SYMBOLS:
        print("Insufficient symbols")
        return

    array_wh = SYMBOLS_PER_CARD - 1
    # possible_cards = array_wh ** 2 + SYMBOLS_PER_CARD

    cards = []

    # start nodes
    start_nodes = list(range(1, SYMBOLS_PER_CARD + 1))
    cards.append(start_nodes)

    # algorithm -> slopes
    for slope in start_nodes:
        for x in range(array_wh):
            card = [slope]

            for y in range(array_wh):
                index = (SYMBOLS_PER_CARD + array_wh * y + ((slope * y + x) % array_wh)) + 1
                card.append(index)

            cards.append(card)

    cards = cards[:MAX_CARDS]

    # print output
    number = 0
    for card in cards:
        print(number, end=' - ')
        print([symbols[sym - 1] for sym in card])
        number += 1


if __name__ == '__main__':
    main()
