# RCCar

## Using the Application
Download android-debug.apk to your Android device and install the application. Connect your phone to the wifi network labeled "RC Car" and launch the application. Hold your phone sideways and place one finger on each side of the screen. Dragging a finger up or down on the screen will modify the speed at which the corresponding tread spins! Move both fingers up to go forward, both down to go backward, or one up and one down to spin in place. To brake, lift your fingers from the screen.

## Understanding and Modifying the Source Files
The code for our control app is written in Java using the LibGDX framework. The source files are located in core/src/science/skywhale/rccar. NetworkDude handles all communication with the car, TouchInput decides what to do when a user taps, drags, or releases a finger, and ControlScreen displays the graphics and holds everything else together.

The car is controlled with a Raspberry Pi. The code onboard the Pi is contained in just one file: car.py, located in the root folder. The gpiozero library is used to control the pins.
