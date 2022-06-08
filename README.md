# Gravity Toy

### Description

A little program I made to simulate gravitational orbits in a fun, satisfying, and visually interesting way!

The program simulates a 2D environment where you can follow the visually fascinating paths according to the Newtonian model of gravity

As of the current version, stars are stationary, and satellites do not exert gravitational force

### Footage

![Previews of the Gravity Toy](gravity_preview.gif)

### Controls:
- Left Click: Place Satellite
- Left Click + Drag: Place Satellite with a velocity relative to the length dragged 
  - Note: Will move in opposite direction to drag, like a slingshot!
- Right Click: Place Star
- Right Click + Drag: Place Star with the radius you drag out. 
  - The star's mass is linearly proportional to the radius
- Middle Click:
  - Place Satellite with a random velocity

### How to Run:

- Requires Java JDK + JRE 16
- Use "java -jar" command to run gravity.jar found in latest github release, or from /out/artifacts/gravity_jar

### Branches:
- Multibody:
  - Based on an early version of the program where all bodies exert and are affected by gravity
  - Much more buggy, not very interactive
  - DEVELOPMENT IN PROGRESS
- Popcorn:
  - Based on a glitchy version during an early implementation of bouncing mechanics
  - Satellites can actually gain momentum from bouncing, so they never stop moving!
  - Would make a fun screensaver
  - No longer in active development
