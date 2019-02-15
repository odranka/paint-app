Oksana Dranka
20558578 

Description: 

1. Menu bar with a file menu. 
	Within file there are options to save and load doodles, 
	to create a new doodle, and to exit the program. 
	Users are prompt to save before overwriting any changes they've made when 
	creating a new doodle, loading a doodle or exiting the program.

2. Colour palette on the left hand side with 6 default colours, 
	a colour selection button (invokes a JColorChooser), 
	and 3 different stroke widths. 
	The selected color is indicated in this panel as well. 	

3. The main area of the screen is a canvas that supports drawing by 
	using mouse strokes. Mouse down begins a stroke, 
	mouse dragged defines the body of a stroke, and mouse up defines the end
	of a stroke, using the appropriate color and line width. 

4. At the bottom of the screen, there are playback controls (JButtons with icons) 
	and a playback bar (JSlider). 
	The playback controls at the bottom include: 
 	- a play button (>) that causes the strokes to be drawn, 
		from the current slider position to the end, 
		as they were drawn on the canvas. 
	- the (->) button rewinds to the start of the animation
	- the (<-) button goes to the end of the animation 
	The playback bar supports scrubbing. 
	- Playback animation includes an aspect of time. 

5. File saving and loading is supported using a JFileChooser. 
	- files will be saved with the extension '.paint'
	- can only open files with '.paint' extension

6. In drawing, if you scrub back, some of your later strokes
	will vanish on the display. At this point, if you begin drawing, 
	the non-displayed strokes are removed and new strokes that 
	you create will be appended to the visible strokes on the display. 
	If you begin drawing partway through a previous stroke,
	that stroke will truncated to just include points up to the 
	new point where you start drawing. 


Enhancements:
- Ability to play animations both forward and backward (buttons < and >) 


To run:
- Gradle JVM version is Java 10
- Source code is located in src/main/java
- Run with: ./gradlew run