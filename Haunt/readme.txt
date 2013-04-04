Michael Cohen period 10

Haunt
This is a text based adventure game. As 
such, it processes user input in relation
to what's going on behind the scenes in
the program and generates a text response
(occasionally an internal response as well)
A bug I encountered was with the display
of the map (see below) in that it showed 
various rooms where they shouldn't have
been due to spacing issues. It's not a big
issue in the long run, though.

Keep in mind that while I do use comments
in some sections, I ran into so many large
coding changes that I lapsed on them. Many
of them are monstrously out of date.

No special compiling instructions, other 
than compiling all the java files in the 
.zip file.

How To:
The program requires a lot of reading, so
pay close attention to what's being said.
These are the commands you can enter:
*Note that every command has to have 
another word next to it with a space 
between. Even though this literally does 
not matter in the least to the last three
commands, you must do this or it won't be
recognized. They're not case-sensitive, by
the way.
LOOK - Get a more detailed description of
something. To learn more about the room,
use "look room". You can't look at items
once they're in your inventory, so keep
track of the details of thing (pen and 
paper is recommended)
GET - Get something. The program will let
you know when you can't pick something up,
so play with that as much as you want.
USE - this applies for things in the room,
items in your inventory, and travel. To 
travel, keep in mind where the doors would 
be as seen from above. Use north door to
travel up, south door to travel down, and
left and right door to travel left and 
right respectively. Remember to use "use" 
before indicating a door. Use commands also
disappear after one use, except for doors,
so keep that in mind.
SOLVE PUZZLE - this one's specific. If 
something seems like it needs a specific 
entry from you, then try this command.
ITEMS - this displays a list of what items
you're holding.*
MAP - this displays a map of the 
house, but it's spaced weird so good luck 
understanding it. Pen and paper 
recommended.
QUIT - quit at any time with this.

Tips for if you're stuck:
-If something seems correct, it more than
likely is. Try checking your inventory for
item names, seeing what the look paragraph
calls things, spelling, etc.
-There are two points in the game where 
you might definitely hit a WALL. Try not
to JUMP to conclusions there. Your items
might not be what you need.
-If you simply cannot move forward, try
running a circuit of the place. Check
everything the paragraphs in each room
describe.
-If all else fails, check the code. The 
answers are all there.
Puzzle answers - in the puzzle classes.
Main game answers - in RoomStory, probably.

Files:
Haunt.java
Manager.java
CommandInput.java
Position.java
RoomStory.java
RoomInventory.java
Item.java
Puzzle.java
ClockPuzzle.java
KnobPuzzle.java
LampPuzzle.java
LibraryPuzzle.java
TelephonePuzzle.java
readme.txt