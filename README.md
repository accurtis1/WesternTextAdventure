## WesternTextAdventure
A western adventure written in Java - a nice improvement from my previous games!

It's actually a mash-up of the past two formats from my Prison Text Adventure and The Streets. It has the same general look as the Prison TA (multiple options to pick from) but it has the depth and scope of The Streets (without the confusing and finicky open-worldness!).

Disclaimer: This is basically one of those "Oh I have some decent ideas for my skill level, let's make an awesome game!" 
***6 months later***
"I pray for the patience to finish this."


### Background
As of this writing (7/11/2017), I'm about to start my:

- Super-senior year of Ball State by credits
- Senior year by class
- Sophomore year of my major, and
- Second-to-last year of schooling

i.e. This is my 4th year at Ball State, my 2nd as a Computer Science major, and I must embark on a 5th year to complete the degree. I just wanted to be ~~obnoxious~~ mysterious with the bullet points.

Anyway, this game was created as both a pet project and, later on, as a class assignment. One of the best things about creating The Streets was designing everything prior to its inception. Somewhere in my house there is a notebook with many pages of cramped writing and strikeouts and sketches and maps and blah blah blah. So I tinkered with a couple of ideas and started doing (minimal) research on a Western world! I really just looked up different types of guns and damage comparisons so I didn't look *too* inept when conjuring up weapons. None of the game is meant to be serious or historically/real-world-ly accurate anyway, but I assumed someone would notice if a .44 magnum dealt more damage than a 16 gauge shotgun.

**Full discretion**, I mention that it was a class assignment so I can make pitiful excuses for myself. The class was the second installment of the Introduction to Programming courses, and I had known Java for a (not very) solid month prior to starting. As stated in the change log below, I started adding features to/fixing/beautifying/refactoring the game prior to publishing it on here, so you won't get to see the unadulterated horror that was the first version. It had decent functionality, but it was literally held up with spit and bubble gum. My attempt to retrieve it from my original class submission did not work, so we're starting with this less-horrid version!

Alright, I'm done self deprecating. After taking a break from development for 3 months in order to learn and utilize JavaFX, I've recently been perusing through my textbook for the upcoming semester (Clean Code by Robert C. Martin, love it so far). After refactoring the JavaFX project (which I will upload here at some point), I took a peek at this game and almost fainted. So ugly. Everything was horrendous. Previously, I had the bull's mindset, and thought that it was such a mess I didn't even want to touch the headache it would cause. Now I'm PUMPED for it. It's like I have a shiny, brand-spanking-new set of problem solving skills, and I'm tackling this monster one function at a time. I'm even excited for the new features I originally kind of gave up on! My pride for this pet project has steadily grown, and I'm finally ready to share it with the world (well...you).

If you see something you hate, like, want fixed, know how to fix, are interested in, are disgusted with, etc. I implore you to email me! I don't get very many outside opinions on my work (and zero detailed opinions or opinions on the code), so I would be insanely receptive to a discussion with you. Actually, the fact that you're reading this right now *really* makes me want to talk with you!

### Game.java
This is the main file, which was a mistake in itself. I definitely should have made a separate **Main.java** to run it, and left the junk in Game. Oh well, live and learn. It also has a lot of unneccessary shit at the beginning, but those were just notes to myself at the time (didn't think I'd ever let anyone else read them). They'll be removed ~~*eventually*~~ and I'll start using this README as my change log instead. That's what I'm supposed to do, right?


# Change log!

#### *02/07/2017 - 04/03/2017*
  - didn't think of starting a change log, but now I see its usefulness

#### *04/04/2017*
  - attempted ideas for new items (teleporter, medic kit, magic stone, custom boots)
  - player wins boots on first cowboy kill
  - boots decrease prices by 10%
  - DRY practice w/ constants method in Player
  - added map & functionality
  - got rid of 'flower' item
  - added 'fists' to cover issue of having no weapons
  - TONS o' formatting changes
  - started prisoner quest
  - added 'rooms' to **Game** and 'index' to all **Rooms** so the map tells you where you're at
  - covered issue of player/merchant not having any items
  - exception handling for player.sellInv()

#### *04/05/2017*
  - some prisoner quest stuff
  - added 'teleporter' to item list and wizard inventory
  - changed the conditions under which items show up in sell menus to prevent irrelevant information
  - removed erroneous border in inventory display

#### *07/11/2017*
  - good to be back!
  - refactored player.inventory and fixed a long-standing bug
  - refactored player.setDamage
  - refactored player.getDamage
  - refactored player.setName
  - created addItem() for player and merchant
  - created npc.addRequirement()
  - created player.healingInventory
  - started refactoring player.heal()
  - **committed to GitHub!**
  - created player.getName()
  - refactored player.constants() to switch instead of ifs
  - created player.weapon
  - refactored player.damage
  - created player.weapon getter/setter
  - removed player.damage getter/setter
  - changed around function placement in Player
  - tried private functions for the first time (!!!) with the healing methods in Player
  - gigantic refactor to the trading feature
  - like seriously ^that was ridiculous
  - switched ALL of the room decision trees to switch statements!
  - added static Strings for wrong turns and invalid responses!!!
  - distributed functions in rooms instead of cramming everything into entry()
  - actually basically just refactored the shit out of all the rooms
  - refactored River's steaming pile of crap that was entry()
  
Going to bed now. I've been working on this thing for 8 hours and have added 0 new features. I feel like experienced developers would get a chuckle out of the exasperation in that statement. Good night/morning.

#### *07/31/2017*
  - gold is displayed during a trade
  - fixed error when a non-integer is chosen during trade
  - fixed formatting for striking and fleeing a fight
  - fixed returning to world after fight
  - did a lot with how weapons are set
  - added item.special
  - removed item.special
  - added player.sellingInventory
  - an appearance by my friend ADD (lots of frivolous formatting)
  - ruined formatting
  - implemented hidden room functionality
  - damn near finished shady quest
