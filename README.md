# BabusyaRoguelike

Terminal game in the style of rogue.
* Single player mode
* Multiplayer mode

## Launching game

To launch game just type these commands in terminal

1. ```git clone https://github.com/vladmosin/BabusyaRoguelike.git```
2. ```cd BabusyaRoguelike```
3. ```git checkout HW4```

Then choose game type of game your want to play:
* Multiplayer
* Single player

### Single player mode

1. Just run game ```./gradlew run```
2. Choose SinglePlayer
3. Have fun playing the game

### Multiplayer mode

1. Start server ```./gradlew run --args="server <port>"```
2. Run client ```./gradlew run```
3. Choose MultiPlayer
4. Input your login, path to server (if you run server on your computer then path to it is ```localhost```) and port on server
5. Have fun playing the game
