/*-----------------------------------------------------------------
RoomStory
This is where the meat of the rooms is, the text bits and their initial locked 
positions. The latter are only used for syncLocks in Room.

The instance variables are:
text - 2D array of Strings consisting of each and every text bit for each room.
locks - 2D array of booleans that sets what rooms at the start of the game can't be
accessed.

Methods
constructor that takes up most of the code consisting of the various text bits and 
the setup for locks(though those setups are separate methods in reality).
textSetup - this sets up each index in text with the appropriate text.
locksSetup - this set up each index in locks with the appropriate booleans

This class in conjunction with Room is used in Position.
*///---------------------------------------------------------------
import java.io.*;
import java.util.*;

public class RoomStory {
  
  private String[][] lookText, getText, useText, lookCue, getCue, useCue;
  private String[] solveText;
  private boolean[][] locks;
  private boolean[] solved;
  
  public RoomStory() {
    lookText = new String[40][8];
    getText = new String[40][1];
    useText = new String[40][4];
    lookCue = new String[40][8];
    getCue = new String[40][1];
    useCue = new String[40][4];
    solveText = new String[40];
    locks = new boolean[40][4];
    solved = new boolean[40];
    listsSetup();
    textSetup();
    locksSetup();
    solvedSetup();
  }
  
  public void listsSetup() {
    for(int i = 0;i < lookText.length;i++) {
      for(int r = 0;r < lookText[i].length;r++)
        lookText[i][r] = "";
    }
    for(int i = 0;i < getText.length;i++) {
      for(int r = 0;r < getText[i].length;r++)
        getText[i][r] = "";
    }
    for(int i = 0;i < useText.length;i++) {
      for(int r = 0;r < useText[i].length;r++)
        useText[i][r] = "";
    }
    for(int i = 0;i < lookCue.length;i++) {
      for(int r = 0;r < lookCue[i].length;r++)
        lookCue[i][r] = "";
    }
    for(int i = 0;i < getCue.length;i++) {
      for(int r = 0;r < getCue[i].length;r++)
        getCue[i][r] = "";
    }
    for(int i = 0;i < useCue.length;i++) {
      for(int r = 0;r < useCue[i].length;r++)
        useCue[i][r] = "";
    }
  }
  
  public void textSetup() {
    int room = 0;
    lookText[room][0] = "You enter the foyer.";
    lookText[room][1] = "The room is spacious, and looks very old.  There is a large table sitting in the center of the room, and there are two doors that lead into other rooms on the left and right.";
    lookCue[room][2] = "TABLE";
    lookText[room][2] = "There is a small card on the table.";
    lookCue[room][3] = "CARD";
    lookText[room][3] = "The card reads: 'You have accepted the challenge.  You will find that the door behind you is locked.  Your only escape will be if you can run through the gauntlet of trials set before you.  Perhaps you will escape with the treasure; perhaps you will not.  We will give you one gift: you can try as many times as you wish; perhaps you will arrive at your desire with this.' You flip the card over.  The back appears to be blank.";
    getCue[room][0] = "CARD";
    getText[room][0] = "You pick up the card and put it in your pocket.";
    room = 22;
    lookText[room][0] = "You enter a dim hallway.";
    lookText[room][1] = "The hallway is dimly lit, and dingy.  It is lined with bricks, and they look like they've never been repaired.  There is a wooden door at the end of the hallway.";
    lookCue[room][2] = "BRICKS";
    lookText[room][2] = "You examine each of the bricks, and discover one is loose.";
    useCue[room][0] = "BRICK";
    useText[room][0] = "You pull out the brick to reveal a hollow space behind it.  Inside is a small key.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You pick up the key and put in your pocket.";
    room = 23;
    lookText[room][0] = "You enter a room with a mirror on the wall.";
    lookText[room][1] = "The room is grand looking, almost in the fashion of a ballroom.  The floors are of hardwood and the walls are covered in fancy wallpaper.  On the right wall is a big mirror and on its right is a single red curtain.  At the north and left ends of the room is a door.";
    lookCue[room][2] = "CURTAIN";
    lookText[room][2] = "There is nothing behind the curtain.";
    room = 24;
    lookText[room][0] = "You enter a bare room with a piano in the center.";
    lookText[room][1] = "The room doesn't have anything interesting in it other than the large, black grand piano in the center. There is a door on the opposite side of the room.";
    lookCue[room][2] = "PIANO";
    lookText[room][2] = "You search inside the piano and find a piece of sheet music.  However, all that's written on it is a word: 'ADD BAG'.";
    getCue[room][0] = "SHEET MUSIC";
    getText[room][0] = "You pick up the sheet music.";
    useCue[room][0] = "PIANO";
    useCue[room][1] = "SHEET MUSIC";
    useText[room][0] = "You have no idea what to play.";
    useText[room][1] = "Using the piece of sheet music you found as a reference, you play the following notes: A, D, D, B, A, and G.  Suddenly, you hear a clicking noise.";
    room = 25;
    lookText[room][0] = "You enter a posh looking living room.";
    lookText[room][1] = "The room feels very cozy but looks way too fancy for your tastes.  There is a thick fur rug on the floor and two brown leather couches.  A door is on the far end of the room.";
    lookCue[room][2] = "COUCHES";
    lookText[room][2] = "You search through the couch cushions.  Sorry, no spare change here.";
    lookCue[room][3] = "RUG";
    lookText[room][3] = "The rug is very plush looking.  You could almost fall asleep in it.  There's a weird looking lump in the center.";
    useCue[room][0] = "RUG";
    useText[room][0] = "You lift up the rug.  There is a strange looking knob underneath it.";
    getCue[room][0] = "KNOB";
    getText[room][0] = "You take the knob and put in your pocket.";
    room = 26;
    lookText[room][0] = "You find yourself in a game room.";
    lookText[room][1] = "There's a set of rifles on the wall; there's a pool table near the wall.  There's also several gambling tables. There are doors on the south and right sides of the room.";
    lookCue[room][2] = "RIFLES";
    lookText[room][2] = "Rifles; yup, those are rifles alright.  You don't have much use for them; they'd just slow you down.";
    lookCue[room][3] = "POOL TABLE";
    lookText[room][3] = "The pool table has a set of pool balls on it in their triangle.";
    lookCue[room][4] = "GAMBLING TABLES";
    lookText[room][4] = "The gambling tables are still set up; you almost feel like playing a game, but then you remember that you're alone.  There are some poker chips scattered about.";
    getCue[room][0] = "POKER CHIP";
    getText[room][0] = "You take a poker chip and put it in your pocket.";
    room = 27;
    lookText[room][0] = "You find yourself in a library.";
    lookText[room][1] = "Just your typical library; plenty of bookshelves, chairs, and tables.  The more unusual item in the room is a giant Tiki statue. There's a door on the north and left ends.";
    lookCue[room][2] = "BOOKSHELVES";
    lookText[room][2] = "The bookshelves have plenty of books, but nothing to interest you.";
    useCue[room][0] = "BOOKS";
    useText[room][0] = "You insisted on searching the books and found something on the mansion.  You begin reading:'The strange happenings at the manor began as soon as it began construction.  We were told that strange things would happen, on account of the ground we were building it on.  We got the land because of rumors that it was haunted.  The locals didn't want it; they were too superstitious.  But we were plagued with problems starting from day one.  It's almost like a builders were possessed because they completely ignored our blueprints.  The house became some kind of twisted, bizarre construct.  But that was just the beginning of our problems...' wait, what's going on?  The rest of the book is missing!  Oh well...";
    lookCue[room][3] = "CHAIRS";
    lookText[room][3] = "The chairs are all pretty boring; nothing there.";
    lookCue[room][4] = "TABLES";
    lookText[room][4] = "On one of the tables is a single book; a phone book.  There's a bookmark sticking out of it.";
    lookCue[room][5] = "PHONEBOOK";
    lookText[room][5] = "You open the phonebook up to the page with the bookmark.  There is a message running across the bookmark to the page.  It says 'death isn't the end'.";
    getCue[room][0] = "BOOKMARK";
    getText[room][0] = "You pick up the bookmark, which itself reads 'death isn't'.";
    lookCue[room][6] = "TIKI";
    lookText[room][6] = "The Tiki statue, strangely enough, has an inscription.  It reads 'look for the pun'.";
    solveText[room] = "Solving the door puzzle appears to have unlocked the door!";
    useCue[room][1] = "NORTH DOOR";
    useText[room][1] = "The door is locked, but not with an ordinary lock.  It's some kind of strange panel.";
    room = 28;
    lookText[room][0] = "You find yourself in a smelly room.";
    lookText[room][1] = "You don't have much expertise in these things, but you think it's a smoking room.  There are a couple of stained green armchairs.  There's also a small door on the left side of the room and one to the North.";
    lookCue[room][2] = "CHAIRS";
    lookText[room][2] = "You search the armchairs, but there's nothing in them.";
    room = 30;
    lookText[room][0] = "You find yourself in a cramped room.";
    lookText[room][1] = "Looking around, you don't see anything except a small panel in the ceiling.";
    room = 29;
    lookText[room][0] = "You enter a seedy looking bar.";
    lookText[room][1] = "The room oozes grime and filth.  It's almost as if it was open to the public, as illogical as that might be.  There are few chairs scattered about the room, but the bar is the most prominent feature of the room.  There's a door on the far side of the room.";
    lookCue[room][2] = "CHAIRS";
    lookText[room][2] = "The chairs don't have anything relevant to your interests.";
    lookCue[room][3] = "BAR";
    lookText[room][3] = "Behind the bar is a rack of various types of liquor, though most of them are empty.  The bar top itself is a stained and dirty wooden affair, with a few glasses on it, all previously used.";
    lookCue[room][4] = "BAR TOP";
    lookText[room][4] = "There's nothing of interest on the bar, but you find a small piece of dried gum under one of the glasses.";
    lookCue[room][5] = "BOTTLES";
    lookText[room][5] = "You examine all the bottles thoroughly.  You discover a key inside one of them.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You put the key in your pocket.";
    useCue[room][0] = "BAR KEY";
    room = 20;
    lookText[room][0] = "You enter a strange new room.";
    lookText[room][1] = "The room is... odd.  It is bare except for a small table in the center, upon which is what looks like the base of a rotary telephone, but with no receiver.  There is a door at the north, south, and left ends of the room.";
    lookCue[room][2] = "TELEPHONE";
    lookText[room][2] = "You examine the telephone. There doesn't seem to be anything unusual about it.";
    solveText[room] = "The number you dialed appears to be correct.  You hear the click of the door unlocking.";
    useCue[room][0] = "NORTH DOOR";
    useText[room][0] = "The door is locked...  Maybe something to do with the telephone?";
    room = 1;
    lookText[room][0] = "You find yourself in what appears to be a coat room.";
    lookText[room][1] = "There are racks of coats on the left and right walls.  There's also a door on the opposite side of the room.";
    lookCue[room][2] = "COATS";
    lookText[room][2] = "You rummage through the coats.  Inside one of the pockets of one of the coats, you find a placard.  It reads: 'You have been formally invited to the housewarming party of our new manor.  We apologize for any strange accommodations you might find, but the servants have been a little strange lately.  They've been hoarding various items, so please watch your belongings carefully.  We hope to see you soon!'...  Well, that was pointless.";
    room = 2;
    lookText[room][0] = "You enter a room with a mirror on the wall.";
    lookText[room][1] = "The room is grand looking, almost in the fashion of a ballroom.  The floors are of hardwood and the walls are covered in fancy wallpaper.  On the left wall is a big mirror, framed by two red curtains.  At the far end of the room is a door.";
    lookCue[room][2] = "LEFT CURTAIN";
    lookText[room][2] = "Behind the left curtain you find a small door.";
    lookCue[room][3] = "CURTAINS";
    lookText[room][3] = "There is nothing behind the right curtain.";
    room = 3;
    lookText[room][0] = "Behind the door is a small closet.";
    lookText[room][1] = "All that is within the closet is a small box on the floor.";
    lookCue[room][2] = "BOX";
    lookText[room][2] = "You open the box to find a knife.";
    getCue[room][0] = "KNIFE";
    getText[room][0] = "You take the knife.";
    room = 4;
    lookText[room][0] = "You enter a dining room.";
    lookText[room][1] = "There's a large table in the center of the room with chairs all around it.  There also several paintings on the walls.  There's a door on the opposite side of the room.";
    lookCue[room][2] = "TABLE";
    lookText[room][2] = "Nothing on the table but candlesticks, and you don't want those.";
    lookCue[room][3] = "CHAIRS";
    lookText[room][3] = "Nothing on the chairs.";
    lookCue[room][4] = "PAINTINGS";
    lookText[room][4] = "The paintings are bland and uninteresting; just family portraits.";
    room = 5;
    lookText[room][0] = "You enter what looks like the kitchen.";
    lookText[room][1] = "The kitchen is fairly clean, considering how long it's been around.  There is a fridge, an oven, and a large counter to prepare food on.  There are various cabinets as well.  There's what looks like a pantry to the north, and a normal door on the left.";
    lookCue[room][2] = "CABINETS";
    lookText[room][2] = "There's nothing in any of the cabinets.";
    lookCue[room][3] = "OVEN";
    lookText[room][3] = "Nothing in the oven.";
    lookCue[room][4] = "FRIDGE";
    lookText[room][4] = "All that's in the fridge is a piece of rotten meat.  Yuck.";
    lookCue[room][5] = "COUNTER";
    lookText[room][5] = "On the counter is a loaf of bread.";
    useCue[room][0] = "BREAD";
    useCue[room][1] = "KNIFE";
    useText[room][0] = "You try to rip apart the bread with your hands, but it's so stale after all these years that it's impossible.";
    useText[room][1] = "Using the knife, you manage to cut the bread.  Inside one of the air bubbles in the bread is a key.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You take the key and put it in your pocket.";
    useCue[room][2] = "NORTH DOOR";
    useText[room][2] = "The pantry is locked.";
    useCue[room][3] = "BREAD KEY";
    room = 7;
    lookText[room][0] = "Using the key you found in the bread, you make it into the pantry.";
    lookText[room][1] = "It's a small room, but there's food on all the shelves.  It's all canned, so it might still be good even today. Doubful, though.";
    lookCue[room][2] = "SHELVES";
    lookText[room][2] = "Searching the shelves, you find a strange knob.";
    getCue[room][0] = "KNOB";
    getText[room][0] = "You take the knob and put it in your pocket.";
    room = 6;
    lookText[room][0] = "You find yourself in a hallway.";
    lookText[room][1] = "Not much talk about here.  Just your standard hallway.  There's a small stand with a vase of flowers on top of it near one of the walls.  There's a door on the north end.";
    lookCue[room][2] = "VASE";
    lookText[room][2] = "You dump the flowers and water out of the vase, and make a mess.  Naughty you.  It was worth it though, you find a weird knob.";
    getCue[room][0] = "KNOB";
    getText[room][0] = "You put the knob in your pocket.";
    room = 8;
    lookText[room][0] = "You enter a sitting room.";
    lookText[room][1] = "It's likely the servants' sitting room.  The armchairs are very cheap looking and there's a small table in the center.  There's a door on the opposite side of the room.";
    lookCue[room][2] = "CHAIRS";
    lookText[room][2] = "Nothing in the armchairs.";
    lookCue[room][3] = "TABLE";
    lookText[room][3] = "Nothing on the table.";
    room = 9;
    lookText[room][0] = "You enter a hallway.";
    lookText[room][1] = "Just a hallway.  Nothing in it really.  There's a door on the opposite end.";
    room = 10;
    lookText[room][0] = "You enter the servants' quarters.";
    lookText[room][1] = "Just what you'd expect from servants' quarters: a small cramped bedroom fitted with several beds.  Next to each bed is a night table.  There's also a painting on the wall and a door on the right.";
    lookCue[room][2] = "BEDS";
    lookText[room][2] = "The beds aren't anything special.  They're still made, but dusty after the time lapse.  Nothing to report.";
    lookCue[room][3] = "NIGHT TABLES";
    lookText[room][3] = "You check each night table.  Inside one you find a sheet of paper.";
    lookCue[room][4] = "PAPER";
    lookText[room][4] = "The sheet of paper has writing on it.  It reads: 'The closeted has three. The living has none. The drowned has four.'... What does that mean?";
    lookCue[room][5] = "PAINTING";
    lookText[room][5] = "You lift the painting off the wall.  Behind the painting, there are three holes of different shapes.";
    lookText[room][6] = "They almost look like the holes where doorknobs would fit on a door.";
    solveText[room] = "Inserting the knobs causes the wall on the north side of the room to slide in, revealing a small door.";
    room = 11;
    lookText[room][0] = "Behind the door is a small closet.";
    lookText[room][1] = "All that's in it is an elaborate key hanging on the wall.";
    lookCue[room][2] = "KEY";
    lookText[room][2] = "The key is very ornate and expensive looking; god knows what it's doing in the servants' quarters.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You take the key and put it in your pocket.";
    room = 12;
    lookText[room][0] = "You find yourself in a washroom.";
    lookText[room][1] = "It's a pretty scummy washroom.  There's a big tub on one side of the room, presumably where they took baths.  There are a few sinks lined up on the other side, all of them fairly oxidized.  There's a door on the opposite side of the room.";
    lookCue[room][2] = "TUB";
    lookText[room][2] = "Inside of the tub is a small plug that was probably used to plug up the drain when they were taking baths.";
    lookCue[room][3] = "PLUG";
    lookText[room][3] = "The plug is about the size of a muffin, with a small chain attached to the end of it. It almost... clings to the metal drain.";
    getCue[room][0] = "PLUG";
    getText[room][0] = "You pick up the plug and put it in your pocket.";
    lookCue[room][4] = "SINKS";
    lookText[room][4] = "The sinks are dirty, but other than that there's nothing unusual about them.";
    room = 13;
    lookText[room][0] = "You emerge in what is probably a storage room.";
    lookText[room][1] = "You think it's a storage room, anyway.  There's a bunch of a indiscernible objects covered in white tarps and a few crates stacked around the room.  There's also a door at the opposite side of the room.";
    lookCue[room][1] = "TARPS";
    lookText[room][1] = "You check under the tarps.  All that you find is dusty furniture.";
    lookCue[room][2] = "CRATES";
    lookText[room][2] = "You look around the crates.  Nothing here.";
    room = 14;
    lookText[room][0] = "You enter what's probably a breakfast room.";
    lookText[room][1] = "You haven't seen one before, but this is what you assume a breakfast room would look like.  There's a small table in the middle of the room, still set up for breakfast.  Naturally, there are few chairs around it as well.  There's a door to the south and left sides.";
    lookCue[room][2] = "TABLE";
    lookText[room][2] = "There are four place settings on the table, all of different colors.  Going clockwise from the top, the colors are as follows: blue, green, yellow, red.";
    lookCue[room][4] = "CHAIRS";
    lookText[room][4] = "Nothing on the chairs.";
    room = 15;
    lookText[room][0] = "You find yourself in a bedroom.";
    lookText[room][1] = "Considering the grandeur of the house, the bedroom is actually quite small.  There's a queen sized bed pushed up against the wall and next to it, a night table.  Opposite the bed is a dresser.  There's a door on the north and right sides of the room.";
    lookCue[room][2] = "BED";
    lookText[room][2] = "All you find in the bed is a lot of dust.";
    lookCue[room][3] = "NIGHT TABLE";
    lookText[room][3] = "The night table is locked.";
    lookCue[room][4] = "NIGHT TABLE";
    lookText[room][4] = "You find a piece of paper and another key.";
    lookCue[room][5] = "PAPER";
    lookText[room][5] = "The piece of paper reads: 'We've been living here for a good two years now.  It's been rather taxing on our minds.  My wife went mad last year and we had to send her to the asylum.  Two of my children have contracted brain fever, and they probably will not live to see the next year.  I am also slowly losing my grip on reality; I hear voices that aren't there... I suspect everyone of wrongdoing.  I'm not sure how long I will live, but I think I should send my remaining children out of this place; hopefully it will be good for them.  As for me... it is already too late...'...  It cuts off there.";
    lookCue[room][6] = "KEY";
    lookText[room][6] = "The key is rather bulky, and looks as if it's even older than the house.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You take the key and put it in your pocket.";
    lookCue[room][7] = "DRESSER";
    lookText[room][7] = "There's nothing in the dresser.";
    useCue[room][0] = "BRICK KEY";
    useText[room][0] = "The key you found in the hallway seems to have unlocked the night table.";
    room = 16;
    lookText[room][0] = "You enter the shabbiest room you've seen yet.";
    lookText[room][1] = "The room's wooden floor is cracked and dirty.  It has several large holes, as do the walls.  Other than that, the room is entirely bare.  There is a door on the north and left sides of the room.";
    lookCue[room][2] = "WALL HOLES";
    lookText[room][2] = "Looking into the holes on the wall, you find nothing.";
    lookCue[room][3] = "FLOOR HOLES";
    lookText[room][3] = "Looking into the holes on the floor, inside one of them you see a glint of metal.";
    useCue[room][0] = "PLUG";
    useText[room][0] = "You correctly deduced that the plug from the bathtub was magnetic, and you use it to fish out a key.";
    lookCue[room][4] = "KEY";
    lookText[room][4] = "The key is very small; it looks like the kind that you use for a bank deposit box.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You take the key and put it in your pocket.";
    room = 17;
    lookText[room][0] = "You emerge in a study.";
    lookText[room][1] = "This is about as stereotypical as studies get.  A large mahogany desk sits in the middle of the room with a luxurious arm chair behind it.  There also some bookshelves and a door on the opposite side of the room.";
    lookCue[room][2] = "DESK";
    lookText[room][2] = "On the desk is a desk lamp.";
    lookCue[room][3] = "LAMP";
    lookText[room][3] = "You examine the lamp minutely.  On its base are four colored buttons.";
    solveText[room] = "Pressing the buttons in that order triggers a clicking noise in the distance.";
    lookCue[room][4] = "CHAIR";
    lookText[room][4] = "Nothing on the arm chair.";
    lookCue[room][5] = "BOOKSHELVES";
    lookText[room][5] = "These bookshelves aren't very interesting; just stuff about stocks and bonds.";
    room = 18;
    lookText[room][0] = "You emerge in a gallery.";
    lookText[room][1] = "Looks like someone was very proud of their art collection.  All kinds of paintings and a few statues inhabit this room.  There's a door on the right and south sides of the room.";
    lookCue[room][2] = "PAINTINGS";
    lookText[room][2] = "The paintings are... nice.  Not really your style, though.";
    lookCue[room][3] = "SCULPTURES";
    lookText[room][3] = "Sculptures...  they really make you think... zzz...";
    room = 19;
    lookText[room][0] = "You emerge in a trophy room.";
    lookText[room][1] = "Lots of stuffed animal heads on the walls, but also plenty of real trophies.  They still have a bit of luster, even with all the dust on them.  There's a door to the left and right.";
    lookCue[room][2] = "ANIMAL HEADS";
    lookText[room][2] = "They're creepy.  Enough said.";
    lookCue[room][3] = "TROPHIES";
    lookText[room][3] = "Looking in each of the trophies, you eventually find a key and one of them.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You take the key and put in your pocket.";
    useCue[room][0] = "TROPHY KEY";
    room = 21;
    lookText[room][0] = "You find yourself in a small room.";
    lookText[room][1] = "All there is in here is a set of stairs going downward.  There's also a door to the south.";
    room = 31;
    lookText[room][0] = "You find yourself in a small room.";
    lookText[room][1] = "All there is in here is a set of stairs going upward.  There's also door to the south.";
    room = 32;
    lookText[room][0] = "You emerge in a room with a pit.";
    lookText[room][1] = "The pit stretches across the entire room, and is its most distinctive feature.  There's a door to the south and north.";
    useCue[room][0] = "SOUTH DOOR";
    useCue[room][1] = "JUMP";
    useText[room][0] = "You weren't careful enough; you fall down the pit and die, wondering how the hell that happened.";
    useText[room][1] = "Congratulations; you were smart enough to jump over the pit.  Now you can use the door.";
    room = 33;
    lookText[room][0] = "You find yourself in a terrifying room.";
    lookText[room][1] = "The room is full of death traps!  There are a bunch of holes in the wall that'll likely spit out arrows at you, and there's a massive swinging axe blade behind those.  Behind everything is a door...";
    useCue[room][0] = "POKER CHIP";
    useText[room][0] = "You try using the poker chip on the arrows.  It doesn't go so well; you die.  If only there was a way past everything...";
    useCue[room][2] = "SOUTH DOOR";
    useText[room][2] = "You try running past the arrows, and make it, but the axe blade gets you.  You die.  If only there was a way past everything...";
    useCue[room][3] = "WALL";
    useText[room][3] = "You knock on the wall, and it slides back, revealing a secret passage.  You walk through it and come out at the end of the room.  You can now use the door.";
    room = 34;
    lookText[room][0] = "As you enter the next room, the door behind you locks.";
    lookText[room][1] = "All there is in this room is the locked door behind you and another door in front of you.";
    useCue[room][0] = "LEFT DOOR";
    useCue[room][1] = "LEFT DOOR";
    useText[room][0] = "You try the door.  It's locked.  You don't have the key, either.  As there is no escape from the room, you slowly starve to death.  You must now restart the game. Type start when you're ready. Hint: check the brick hallway and the bedroom.";
    useText[room][1] = "You try the door.  It's locked.  Fortunately, the bulky key you found in the bedroom night stand fits the lock.";
    room = 35;
    lookText[room][0] = "You move into the next room, and the door locks behind you.";
    lookText[room][1] = "The room appears to be a wine cellar, with plenty of barrels of what you assume to be wine.  There's a door on the north side of the room, and the locked door on the south.";
    lookCue[room][2] = "BARRELS";
    lookText[room][2] = "You check the barrels.  Behind one of them you find a key.";
    getCue[room][0] = "KEY";
    getText[room][0] = "You pick up the key.";
    useCue[room][0] = "CELLAR KEY";
    room = 36;
    lookText[room][0] = "You enter a room that looks like a bank vault.";
    lookText[room][1] = "There are lots of what look like safety deposit boxes on the walls.  There's also a door on the opposite end of the room.";
    lookCue[room][2] = "SAFETY DEPOSIT BOXES";
    lookText[room][2] = "You'd love to look inside the safety deposit boxes... but they're all locked.";
    useCue[room][0] = "NORTH DOOR";
    useCue[room][1] = "NORTH DOOR";
    useText[room][0] = "The door is locked.  You can't find a key anywhere in the room, and you didn't find one upstairs; with no way out, you starve to death.  You must restart the game.  Type start when you're ready. Hint: look through the bare room and the washroom.";
    useText[room][1] = "The door is locked.  Fortunately, the key you found in the floor of the bare room fits it and you're able to progress.";
    room = 37;
    lookText[room][0] = "Upon entering the next room, the door locks behind you.";
    lookText[room][1] = "The room is full of clocks; on the walls and on the floor.  There's a door on the opposite end of the room with a keyboard on it.";
    lookCue[room][2] = "CLOCKS";
    lookText[room][2] = "The clocks all show different times, so no hints there.";
    lookCue[room][3] = "KEYBOARD";
    lookText[room][3] = "It's a standard QWERTY keyboard, just in typewriter fashion (Weird...).";
    useCue[room][0] = "NORTH DOOR";
    useText[room][0] = "The door is locked...  Maybe something to do with the typewriter?";
    solveText[room] = "Pressing the L key seems to unlock the door.";
    room = 38;
    lookText[room][0] = "You emerge into a small room.";
    lookText[room][1] = "There are two things in this room: a treasure chest in the center and a ladder that leads upward.  You try every key in your arsenal to unlock the treasure chest, but nothing works.  You can't even lift the chest off the floor because it's bolted down. You're forced to leave; alive, but as poor as you were when you came in.  Oh well, at least you got your adventure. THE END Hint: Next time, try paying more attention to the help.";
    lookText[room][2] = "There are two things in this room: a treasure chest in the center and a ladder that leads upward.  You find that the expensive key you found in the servants' quarters fits the lock, and you open the treasure chest to find more money than you ever seen before in one place.  You fill your coat with the stuff, and leave the place; alive, rich, and happy. THE END Congratulations. Winers don't use mugs.";
    room = 39;
    lookText[room][0] = "Pulling the panel down, a ladder drops.  You go up and find yourself in an attic.";
    lookText[room][1] = "It's more of a crawl space, really.  There's a lot of junk scattered around, not the least of which is a box in the back of the space.";
    lookCue[room][2] = "BOX";
    lookText[room][2] = "Inside the box is a bunch of junk.  A small alarm clock catches your eye.";
    lookCue[room][3] = "CLOCK";
    lookText[room][3] = "The time on the clock is 3:00.  Other than that, there's nothing unusual about it.";
    getCue[room][0] = "CLOCK";
    getText[room][0] = "You pick up the clock and put it in your jacket pocket.";
  }
  
  public void locksSetup() {
//note that increasing clockwise in four cardinals, 0 is at the north of the room
    int room = 0;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 1;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 2;
    locks[room][0] = true;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = false;//look left curtain first
    room = 3;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = false;
    room = 4;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 5;
    locks[room][0] = false;//need bread key
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = true;
    room = 6;
    locks[room][0] = true;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = false;
    room = 7;
    locks[room][0] = false;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 8;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 9;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 10;
    locks[room][0] = false;//solve knob puzzle
    locks[room][1] = true;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 11;
    locks[room][0] = false;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 12;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 13;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 14;
    locks[room][0] = false;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = true;
    room = 15;
    locks[room][0] = true;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = false;
    room = 16;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 17;
    locks[room][0] = false;//solve lamp puzzle
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 18;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 19;
    locks[room][0] = false;
    locks[room][1] = false;//find key in trophy room
    locks[room][2] = false;
    locks[room][3] = true;
    room = 20;
    locks[room][0] = false;//solve telephone puzzle
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;//see lock[19][1]
    room = 21;
    locks[room][0] = false;//special case, leads downstairs if use stairs
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 22;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 23;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 24;
    locks[room][0] = false;//solve piano puzzle
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 25;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 26;
    locks[room][0] = false;
    locks[room][1] = true;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 27;
    locks[room][0] = false;//solve door panel puzzle
    locks[room][1] = false;
    locks[room][2] = false;
    locks[room][3] = true;
    room = 28;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = true;
    room = 29;
    locks[room][0] = false;//find bar key
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 30;
    locks[room][0] = false;//special case, leads to attic if use panel
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = false;
    room = 31;
    locks[room][0] = false;//special, leads back upstairs if use stairs
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 32;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = true;//keep in mind if jump isn't used before, you die.
    locks[room][3] = false;
    room = 33;
    locks[room][0] = true;
    locks[room][1] = false;
    locks[room][2] = true;//only using wall before opening door will avoid death
    locks[room][3] = false;
    room = 34;
    locks[room][0] = false;//can't go back upstairs after this point
    locks[room][1] = false;
    locks[room][2] = false;
    locks[room][3] = false;//need old key to move, otherwise death
    room = 35;
    locks[room][0] = false;//find cellar key
    locks[room][1] = true;
    locks[room][2] = false;
    locks[room][3] = false;
    room = 36;
    locks[room][0] = false;//need safety deposit key, otherwise death
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 37;
    locks[room][0] = false;//solve clock puzzle
    locks[room][1] = false;
    locks[room][2] = true;
    locks[room][3] = false;
    room = 38;
    locks[room][0] = false;//would lead outside, but game ends here
    locks[room][1] = false;
    locks[room][2] = false;
    locks[room][3] = false;
    room = 39;
    locks[room][0] = false;
    locks[room][1] = false;
    locks[room][2] = false;//special, leads back downstairs if use panel
    locks[room][3] = false;
  }
  
  public void solvedSetup() {
    for(int i = 0;i < solved.length;i++)
      solved[i] = true;
    int room = 2;
    solved[room] = false;
    room = 5;
    solved[room] = false;
    room = 6;
    solved[room] = false;
    room = 10;
    solved[room] = false;
    room = 15;
    solved[room] = false;
    room = 16;
    solved[room] = false;
    room = 17;
    solved[room] = false;
    room = 19;
    solved[room] = false;
    room = 20;
    solved[room] = false;
    room = 22;
    solved[room] = false;
    room = 24;
    solved[room] = false;
    room = 25;
    solved[room] = false;
    room = 27;
    solved[room] = false;
    room = 29;
    solved[room] = false;
    room = 32;
    solved[room] = false;
    room = 33;
    solved[room] = false;
    room = 34;
    solved[room] = false;
    room = 35;
    solved[room] = false;
    room = 36;
    solved[room] = false;
    room = 37;
    solved[room] = false;
    room = 38;
    solved[room] = false;
  }
  
  public int getLocksLength(int depth) {
    if(depth == 0)
      return locks.length;
    else
      return locks[0].length;
  }
  
  public boolean getLocks(int row, int col) {
    return locks[row][col];
  }
  
  public int getSolvedLength() {
    return solved.length;
  }
  
  public boolean getSolved(int index) {
    return solved[index];
  }
  
  public String getLook(int row, int col) {
    return lookText[row][col];
  }
  
  public String getGet(int row, int col) {
    return getText[row][col];
  }
  
  public String getUse(int row, int col) {
    return useText[row][col];
  }
  
  public String getSolve(int room) {
    return solveText[room];
  }
  
  public String getLookCue(int row, int col) {
    return lookCue[row][col];
  }
  
  public int getLCLength(int room) {
    return lookCue[room].length;
  }
  
  public String getGetCue(int row, int col) {
    return getCue[row][col];
  }
  
  public int getGCLength(int room) {
    return getCue[room].length;
  }
  
  public String getUseCue(int row, int col) {
    return useCue[row][col];
  }
  
  public int getUCLength(int room) {
    return useCue[room].length;
  }
  
  public void removeUC(int room, String s) {
    for(int i = 0;i < useCue[room].length;i++) {
      if(useCue[room][i].equals(s))
        useCue[room][i] = "";
    }
    
  }
  
  public void removeGC(int room, String s) {
    for(int i = 0;i < getCue[room].length;i++) {
      if(getCue[room][i].equals(s))
        getCue[room][i] = "";
    }
  }
}