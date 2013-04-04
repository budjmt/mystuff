#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <allegro5/allegro.h>
#include <allegro5/allegro_native_dialog.h>
#include <allegro5/allegro_font.h>
#include <allegro5/allegro_ttf.h>
#include <allegro5/allegro_primitives.h>

/*------------------------------------
Minesweeper

Game Flow:

X,Y, and Z are set in terminal

Grid of X by Y tiles is generated with Z mines
The grid is run through and another is generated with number of mine neighbors
Game Begins
Player clicks a space
If flagged, ignore
Checks neighbor grid
If mine, game over and all mines are displayed
Else, number is displayed
Are XY - Z spaces clicked?
If so, Game won
Else, repeat steps
If reset (R) is pressed at any time, board is regenerated from beginning
If X on window is clicked at any time, program is exited

-------------------------------------*/

const int COLORS[8][3] = { {0,0,255}, {0,128,0}, {255,0,0},
			{0,0,128}, {119,15,5}, {127,255,212},
			{138,43,226}, {0,0,0} };
const int GRID_COLOR = 200;
const int CLICK_COLOR = 100;
const int SCREENX = 350, SCREENY = 150;

struct space {
  int num, clicked, flagged;
  int x, y;
  struct space * next, * prev;
};

typedef struct space * space;
typedef space * grid;

space makeSpace(int i, int j) {
  space new = (space)malloc(sizeof(struct space));
  new->num = 0;
  new->clicked = 0;
  new->flagged = 0;
  new->x = i;
  new->y = j;
  new->next = NULL, new->prev = NULL;
  return new;
}

void printGrid(grid g, int x, int y) {
  while(y>0) {
    space temp = *g;
    while(temp) {
      printf("%d ", temp->num);
      temp = temp->next;
    }
    printf("\n");
    g++;
    y--;
  }
}

void incNums(space a) {
  if(a->num >= 0)
    a->num++;
  if(a->prev && a->prev->num >= 0)
    a->prev->num++;
  if(a->next && a->next->num >= 0)
    a->next->num++;
}

void placeMine(grid gr, int x, int y) {
  int rx = rand() % x, ry = rand() % y;
  grid g = gr;
  for(ry;ry > 0;ry--)
    g++;
  space temp = *g;
  for(rx;rx > 0;rx--)
    temp = temp->next;
  if(!temp->num)
    temp->num = -1;
  else {
    placeMine(gr, x, y);
  }
}

grid setNums(grid gr, int xc, int yc, int z) {
  grid g = gr;
  srand(time(NULL));
  for(z;z > 0;z--,g = gr) {
    placeMine(g, xc, yc);
  }
  grid f = NULL, h = g + 1;
  while(g) {
    space temp1, temp2 = *g, temp3;
    if(f)
      temp1 = *f;
    if(h)
      temp3 = *h;
    while(temp2) {
      if(temp2->num < 0) {
	if(f)
	  incNums(temp1);
	incNums(temp2);
	if(h)
	  incNums(temp3);
      }
      if(f)
	temp1 = temp1->next;
      if(h)
	temp3 = temp3->next;
      temp2 = temp2->next;
    }
    f = g;
    g++;
    if(g == gr + yc)
      g = 0;
    if(h)
      h++;
    if(h == gr + yc)
      h = 0;
  }
  return gr;
}

grid initGrid(int x, int y, int z) {
  int i, j;
  grid g = (grid)malloc(sizeof(space) * (y + 1));
  grid f = g;
  for(j = 0;j < y;j++) {
    space curr = NULL, first = NULL;
    for(i = 0;i < x;i++) {
      space new = makeSpace(i,j);
      if(curr)
	curr->next = new;
      else
	first = new;
      new->prev = curr;
      curr = new;
    }
    *g = first;
    g++;
  }
  g = f;
  g = setNums(g, x, y, z);
  return g;
}

void freeGrid(grid g, int x, int y) {
  while(y>0) {
    int x1 = x;
    while(x1>0) {
      space temp = *g;
      *g = (*g)->next;
      free(temp);
      x1--;
    }
    g++;
    y--;
  }
}

void drawFlag(int dx, int dy, int box, int flagged) {
  if(flagged)
    al_draw_filled_rectangle(dx, dy, dx + box - 2, dy + box - 2
		      , al_map_rgb(GRID_COLOR, GRID_COLOR, GRID_COLOR));
  else {
    al_draw_line(dx + box / 2, dy + 1, dx + box / 2, dy + box - 3
		 , al_map_rgb(0,0,0), 2);
    al_draw_filled_triangle(dx + box / 2 + 1, dy + 2, dx + box / 2 + 1
			    , dy + box / 2 + 2, dx +  7 * box / 8
			    , dy + box / 4 + 2, al_map_rgb(255, 0, 0));
  }
  al_flip_display();
}

void flagSpace(space s, int box) {
  //draws/erases a flag on the space, changes s->flagged to 1/0
  int dx = s->x * box + 1;
  int dy = s->y * box + 1;
  drawFlag(dx, dy, box, s->flagged);
  s->flagged = !s->flagged;
}

void drawMine(int x, int y, int box) {
  //draws a mine
  int dx = x * box + box / 2 - 1;
  int dy = y * box + box / 2 - 1;
  al_draw_filled_circle(dx, dy, box / 2, al_map_rgb(0, 0, 0));
}

void drawMines(grid g, int box) {
  //goes through the grid and displays all the mines
  while(g) {
    space temp = *g;
    while(temp) {
      if(temp->num < 0)
	drawMine(temp->x, temp->y, box);
      temp++;
    }
    g++;
  }
}

void drawSpace(space s, int box, ALLEGRO_FONT * font) {
  //draws the number in the middle of the box, changes space to clicked
  if(s->clicked || s->flagged)
    return;
  int dx = s->x * box + box / 2 - 1;
  int dy = s->y * box;
  char sn[2];
  sprintf(sn, "%d", s->num);
  printf("%s, %d\n", sn, s->num);
  al_draw_filled_rectangle(dx - box/2 + 1, dy, dx + box/2, dy + box - 1
			   , al_map_rgb(CLICK_COLOR, CLICK_COLOR, CLICK_COLOR));
  if(s->num != 0)
    al_draw_text(font, al_map_rgb(COLORS[s->num-1][0], COLORS[s->num-1][1]
				  , COLORS[s->num-1][2])
		 , dx, dy, ALLEGRO_ALIGN_CENTRE, sn);
  al_flip_display();
  s->clicked = 1;
}

space findSpace(grid g, int mx, int my, int box, int x, int y) {
  //calculates which space is being clicked
  int sx = mx/box;
  if(sx >= x)
    return NULL;
  int sy = my/box;
  if(sy >= y)
    return NULL;
  for(sy;sy>0;sy--)
    g++;
  space temp = *g;
  for(sx;sx>0;sx--)
    temp = temp->next;
  return temp;
}

void makeDisp(ALLEGRO_DISPLAY ** d, int box, int x, int y) {
  //makes the display with the boxes
  int sx = box * x;
  int sy = box * y;
  *d = al_create_display(sx, sy);
  al_clear_to_color(al_map_rgb(GRID_COLOR, GRID_COLOR, GRID_COLOR));
  int i;
  for(i = 0;i <= sx;i += box)
    al_draw_line(i, 0, i, sy, al_map_rgb(0,0,0), 1);
  for(i = 0;i <= sy;i += box)
    al_draw_line(0, i, sx, i, al_map_rgb(0,0,0), 1);
  al_set_window_position(*d, SCREENX, SCREENY);
  al_flip_display();
}

void reset(int x, int y, int z, int box, grid * g, ALLEGRO_DISPLAY ** d) {
  //sets up a new game
  *g = initGrid(x, y, z);
  printGrid(*g, x, y);
  makeDisp(d, box, x, y);
}

void endGame(grid g, int x, int y, ALLEGRO_DISPLAY * d, ALLEGRO_EVENT_QUEUE * q
	     , ALLEGRO_FONT * f) {
  //kills everything
  freeGrid(g, x, y);
  al_destroy_display(d);
  al_destroy_event_queue(q);
  al_destroy_font(f);
  exit(0);
}

int main(int argc, char ** argv) {
 
  int X = 8, Y = 8, Z = 10;
  char * s;

  if(argc < 4 || atoi(argv[3]) >= (atoi(argv[1]) * atoi(argv[2])) || 
     argv[1][0] < '1' || argv[2][0] < '1' || argv[3][0] < '1')
    s = "defaults";
  else {
    s = "custom";
    X = atoi(argv[1]);
    Y = atoi(argv[2]);
    Z = atoi(argv[3]);
  }
  printf("Using %s, %d x %d, %d mines.\n", s, X, Y, Z);

  int numLeft = X * Y - Z;
  int gameOver = 0;

  int BOX_D = 20;

  al_init();
  al_init_primitives_addon();
  al_install_keyboard();
  al_install_mouse();
  al_init_font_addon();
  al_init_ttf_addon();

  ALLEGRO_DISPLAY * display = NULL;
  ALLEGRO_EVENT_QUEUE * queue = al_create_event_queue();
  ALLEGRO_FONT * arial10 = al_load_font("Arial.ttf", 10, 0);

  grid g;
  reset(X, Y, Z, BOX_D, &g, &display);

  al_register_event_source(queue, al_get_keyboard_event_source());
  al_register_event_source(queue, al_get_mouse_event_source());
  al_register_event_source(queue, al_get_display_event_source(display));

  while(1) {
    while(numLeft && !gameOver) {
      int mx = -1, my = -1, right = 0;
      ALLEGRO_EVENT ev;
      al_wait_for_event(queue, &ev);
      
      if(ev.type == ALLEGRO_EVENT_KEY_DOWN) {
	if(ev.keyboard.keycode == ALLEGRO_KEY_R) {
	  printf("Arrrgh\n");
	  freeGrid(g, X, Y);
	  al_destroy_display(display);
	  reset(X, Y, Z, BOX_D, &g, &display);
	  numLeft = X * Y - Z;
	  gameOver = 0;
	}
      }
      else if(ev.type == ALLEGRO_EVENT_DISPLAY_CLOSE) {
	printf("Time to go\n");
	endGame(g, X, Y, display, queue, arial10);
      }
      else if(ev.type == ALLEGRO_EVENT_MOUSE_BUTTON_DOWN) {
	printf("CLICK\n");
	mx = ev.mouse.x;
	my = ev.mouse.y;
	if(ev.mouse.button & 2)
	  right = 1;
	space s = findSpace(g, mx, my, BOX_D, X, Y);
	if(right) {
	  printf("right\n");
	  flagSpace(s, BOX_D);
	}
	else {
	  if(s->num < 0) {
	    printf("MINE\n");
	    drawMines(g, BOX_D);
	    gameOver = 1;
	  }
	  else {
	    printf("Draw a %d\n", s->num);
	    drawSpace(s, BOX_D, arial10);
	  }
	}
	if(!gameOver)
	  numLeft--;
      }
    }
      
      if(gameOver)
	al_show_native_message_box(NULL, NULL, NULL
				   , "Game Over. Try again with 'R'", NULL, 0);
      else
	al_show_native_message_box(NULL, NULL, NULL
				   , "Congratulations! Play again with 'R'"
				   , NULL, 0);
      
      ALLEGRO_EVENT ev;
      al_wait_for_event(queue, &ev);
      
      if(ev.type == ALLEGRO_EVENT_KEY_DOWN) {
	if(ev.keyboard.keycode == ALLEGRO_KEY_R) {
	  freeGrid(g, X, Y);
	  al_destroy_display(display);
	  reset(X, Y, Z, BOX_D, &g, &display);
	  numLeft = X * Y - Z;
	  gameOver = 0;
	}
      }
      else if(ev.type == ALLEGRO_EVENT_DISPLAY_CLOSE)
	endGame(g, X, Y, display, queue, arial10);
    }

    return 0;
}
