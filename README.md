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

## Theory tasks for exercise 2

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
-Architectural patterns has a wider scope and are used to solve a common reccuring problem in software architecture.
-Desing patterns however are used to solve specific problems and are typically lower level.

4.b)
Template Method:
State works as template class
MenuState, PlayState, PongState and GameOver all extends the State class.

Observer:

4.c)
Advantages in using Template Method:
Since we have several states, using template method we can define general methods and implement the specialized methods.

Disadvantages:
Problem is that we have to implement several almost identical pieces of code

