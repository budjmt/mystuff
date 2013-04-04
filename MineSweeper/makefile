LIBS := allegro-5.0 allegro_dialog-5.0 allegro_primitives-5.0 allegro_font-5.0 allegro_ttf-5.0
SPECIAL := $(shell pkg-config --cflags --libs $(LIBS))

minesweeper: game.c
	$(CC) -o $@ $^ $(SPECIAL)

clean:
	rm minesweeper
	rm *~