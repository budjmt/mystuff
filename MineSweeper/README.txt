Michael Cohen, Shanshan Chen

To compile, make minesweeper

To run, ./minesweeper and play

Rules are as minesweeper

Click to reveal, right click to flag, and press 'r' to reset the game

FILES:
Arial.ttf
game.c
makefile
README.txt
BUGS.txt
TODO.txt
DESIGN.txt
design_doc.txt

NOTE
If there are problems running or compiling the game, make sure you've followed these instructions. If all else fails, put the allegro git repository in the cwd.

1. sudo apt-get update

2. sudo apt-get install libgl1-mesa-dev libglu1-mesa-dev cmake build-essential make libxcursor-dev xorg-dev

3. git clone git://git.code.sf.net/p/alleg/allegro

4. cd allegro

5. git checkout 5.0 [or whatever version you'd want to use; this is the one I'm using]

6. mkdir build; cd build

7. cmake .. ; make ; sudo make install
