# Project-5

Private variables:
I had originally started by creating all the components that were needed to make the GUI match the one in the example picture. This
included a few JButtons, a few JTextFields, a few JLabels, a JSlider, a JComboBox, and a JTextArea box. While I didn't initially initialize
all of them, I would later come back and initialize them, messing around with the sizes to get them just right. In addition, I created a
private String ArrayList, which held all of the STIDs from Mesonet.txt. I would later go on to also add a few more JComboBoxes, JLabels,
JButtons, and JTextFields to incorporate something on the right half of the screen.

readToArray method:
As I began to work on the ActionListeners, I was realizing that I was going to need access to the Mesonet.txt file through an array. So, I
went back to Project 1 and found my read method. However, I modified it from a return type of void to an ArrayList of String type. The
reason I did that was so that I would only have to call it once throughout the whole class. Another change I had to make was deleting the
extra readLine() calls and changing the indeces on the substring, decisions that were made based on how the text file was formatted. It
uses the BufferedReader and goes through each line of the file one at a time, added it to an ArrayList.

measureNode method:
While I was brainstorming how to get actions to follow certain buttons, I realized that we created a method in Project 1 which would
directly help me with the Calculate HD part of the GUI. This method takes an STID in the form of a String and uses a double for loop to
compare each letter of the parameter with each letter of every STID in the Mesonet.txt file. A counter moves up 1 when a letter matches,
and that counter is then read and, depending on what the value is (1,4), one of the four indeces of an ArrayList are added by one,
signifying that there was an STID that was n Hamming Distance away.

findList method:
For the "Show Station" button, I decided to create a method that resembled measureNode. However, instead of returning the number of STIDs
at certain Hamming Distance away, it would return a list of all STIDs in String form, at a given Hamming Distance away.

compareTo method:
As one of my implementations for the right side, I decided to use the compareTo method from Project 1. The method takes two STIDs and
returns an integer that reflects how many of their letters don't match. This was done through a for loop which compared each letter to each
other.

calAverage method:
As my final implementation on the right side, I decided to incorporate the calculate average method from Project 3. The method returns a
rounded average in the form of an integer, and it is done by finding the number value of each of the four letters, adding them together,
and dividing by four.

HammingDistanceGUI (constructor):
Most of the code written for the entire project is in the constructor. While I went over all the methods first, I had started on the
constructor first when I started the project. The methods were incorporated as I ran into obstacles in the constructor. I first started
by sizing up the JFrame and panels. I created two panels, one for the left and one for the right. After that, I went on to create 11
JPanels and added them to the left JPanel. Each panel consisted of the different components, in order of how the example was. While
doing this, I also made sure to set certain text boxes as uneditable, and I formated different things that needed to be changed, such as
the slider showing the tick marks and numbers. Most of the help I needed came from the Java API and help forums, along with past
examples that we did in class. Once everything was laid out the way I wanted it to be, I began to work on the ActionListener part of the
GUI. Although I was initially confused on where to even start, I looked at a past lab and was able to make sense of everything. After
that, the ActionListener parts were fairly easy to write. I started with the slider, firsting trying to get the number in the top box to
change when the slider changed. Once I figured that out, and my confidence went up, I moved on to the Calculate HD button. Using the 
measureNode method that I mentioned earlier, I was able to individually set each of the four text boxes to the corresponding values from
the returned ArrayList. I would then repaint each box. After that, I moved on to the add station button. After some research, I found
that all I had to do was get the typed word from the text box and set it equal to an object. Then, I added that object to the ComboBox.
Finally, I went back to work on the show station button. I used the find list method, using the slider value and the comboBox value to
find the full list of STIDs.

