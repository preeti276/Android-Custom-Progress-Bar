# Android-Custom-Progress-Bar

This source code will help you create a custom progress bar as shown below for your android app. It is bundled with animations and colors to enhace the UI of your android app.

<p align = "center">
  <img src = "https://github.com/preeti276/Android-Custom-Progress-Bar/blob/master/app/src/main/res/drawable/screen.gif">
  </p>
  
  <h3> Implementation </h4>
    A main activity with this progress bar shows a particular fragment with a corresponding popping icon and text.
   The number of fragments displayed are equal to progress indicator icons on this progress bar. This number can be 
   changed as per your use.
 
 <h3> Important Files and Significance </h3>
 
 <a href = "https://github.com/preeti276/Android-Custom-Progress-Bar/blob/master/app/src/main/java/com/example/customprogressbar/CustomWaveView.java"></a> CustomWaveView: This class extend the base View class and creates the background curve view in onDraw method.
  <br>
 
 <a href = "https://github.com/preeti276/Android-Custom-Progress-Bar/blob/master/app/src/main/java/com/example/customprogressbar/CustomProgressViewGroup.java"></a> CustomProgressViewGroup:
  This java class extends the base ViewGroup Class and adds the background, icons and text as children at particular co-ordinates.
  <br>
  
  <a href = "https://github.com/preeti276/Android-Custom-Progress-Bar/blob/master/app/src/main/java/com/example/customprogressbar/ProgressBarActivity.java"></a>ProgressBarActivity: This activity is your main activity with your progress bar and a frame layout to display fragments.
  <br>
  
  <a href = "https://github.com/preeti276/Android-Custom-Progress-Bar/blob/master/app/src/main/java/com/example/customprogressbar/FragmentView.java"></a>FragmentView: This is a default view for fragment. You can change the view and designs, and even add new fragments to display.
  <br>
  
  <h3> All you need to do </h3>
  <ul>
  <li>Clone/download the source code</li>
  <li>Put the images of icon in the res/drawable folder with their names as "icon1", "icon2", etc.</li>
  <li>Put the correspoding text in res/values/strings.xml folder with names as "text1", "text2", etc.</li>
  <li>Add data/design to fragments if required. </li>
  <li>No need to make any changes in ProgressBarActivity which acts like your MainActivity.</li>
 </ul>
 
 <h5>NOTE</h5>
  Uncomment the fill color code in <a href = "https://github.com/preeti276/Android-Custom-Progress-Bar/blob/master/app/src/main/java/com/example/customprogressbar/CustomWaveView.java"></a> CustomWaveView if you need the view with color as shown below. Though the recommended way is to use is without fill loop (as shown above) so as to improve the efficiency since the fill color tends to slow down the app performance. 
   
 <p align="center">
 <img src = "https://github.com/preeti276/Android-Custom-Progress-Bar/blob/master/app/src/main/res/drawable/fill_ss.png">
  </p>
  
  
  









