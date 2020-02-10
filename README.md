# Helicopter

Two small games made in android studio.
Tutorial used: https://www.youtube.com/watch?v=rzBVTPaUUDg&list=PLZm85UZQLd2TPXpUJfDEdWTSgszionbJy 

Here are some pictures of the games:

![Menu](https://user-images.githubusercontent.com/44194036/73175580-d2c7a880-410a-11ea-80c5-3f0da7391ada.PNG)

**HeliGo**

![HeliGo1](https://user-images.githubusercontent.com/44194036/73175622-e6730f00-410a-11ea-94dd-acf1ee7b6e07.PNG)

![HeliGo2](https://user-images.githubusercontent.com/44194036/73175638-ee32b380-410a-11ea-865e-e5d112956202.PNG)

**Pongy**

![Pong1](https://user-images.githubusercontent.com/44194036/73175654-f4289480-410a-11ea-86a3-1c5e2d10054c.PNG)

![Pong2](https://user-images.githubusercontent.com/44194036/73175662-f985df00-410a-11ea-8bf1-e39f9582293d.PNG)

![Pong3](https://user-images.githubusercontent.com/44194036/73175676-ff7bc000-410a-11ea-8123-00e44834c690.PNG)

## Exercise 2

### Step 1

We have chosen task4 Pong game from the first exercise as our base

### Step 2

We have chosen to implement a Singleton pattern for our Ball class(we
created a new class called SingleBall so we keep our Ball class but it
isn't used), since all Pong traditional pong games deals with only one
ball object at a time.

### Step 3

We have followed this flappy bird [tutorial](https://www.youtube.com/watch?v=rzBVTPaUUDg&list=PLZm85UZQLd2TPXpUJfDEdWTSgszionbJy)
to get used to libGDX, where we have used the template and state pattern.

MenuState, PlayState(from helicopter tasks), PongSingleState, GameOverState
extends the abstract class state and inherits its methods and variables.
They are utilizing the template method.

State pattern is realized through having gamestates to keep track of
which objects, game behaviour the game should load, and dispose of when
we leave our previous state. The class GameStateManager manages and keeps
track of our current gamestate.


### Theory tasks for exercise 2

4.a)

Architectural patterns:

-Model view controller

-Pipe and filter

-Entity Component System

Design patterns:

-Observer

-State

-Template Method

-Abstract Factory


Relationships:

-They both deal with solving problems in the software application

Differences:

-Architectural patterns has a wider scope and are used to solve a common
 recurring problem in software architecture.

-Design patterns however are used to solve specific problems and are
typically lower level.

4.b)
Template Method:

State works as template class

MenuState, PlayState, PongState, PongSingleState and GameOverState all
extends the State class.

Observer:

4.c)

Template Method:

Advantages:

Since we have several states, using template method we can define
general methods and implement the specialized methods.

Disadvantages:

Problem is that we have to implement several almost identical pieces of
code. for example in the states [folder](https://github.com/harkamalsi/Helicopter/tree/master/core/src/com/mygdx/game/states)
we have PongSingleState and PongMultiState where the main difference is
in single there is 1 player against the computer and in multi there are
2 players.

State Method:

Advantages:

Easy to keep track of which state we are in and load objects and behaviours,
and dispose of objects not longer needed when transitioning to a the next
state. It can reduce complexity and can remove the need to use switch
statements for objects that has different behaviour.

Disadvantages:

The FSM scheme can quickly grow large needing a lot of code which can be
similar.


