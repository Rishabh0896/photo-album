############################################################################################
****************************************README**********************************************
Design Explanation:

I have used the MVC Pattern to build the application and each of the java classes
have been seprated out logically into the specific package.

Changes done in model post Assignment 7 :
1. Added an identifier(enum) for each IShape child so that the model can check that
   while adding/modifying and deleting shapes.
2. Added a deep copy method in shapes so that Snapshot can have a map of shapes.
3. Added a method movePivot(double, double) to support addition of shapes.

Some things unique about my design :
1. I have used an identifier(enum) for each shape ShapeType.java.
   So say that the user wants to add a new Shape in the future (say Circle), they can
   add the concrete implementation of the class and then add the shape in the enum 
   and override the appendToBuilder and drawShape methods for the specific shape.
   NO OTHER CODE CHANGES ARE NEEDED FOR THE SAME :D As the View calls these methods
   to build the view.
2. I have used a resources/Template.html to render the Webview of the Photo Album.
   (Reason : The user can modify and cheange the CSS as and when needed so the border
   is configurable. This gives more control to the user).
3. I have used a property file resources/config.properties which has all the necessary
   properties that makes the application configurable.
   (For ex: if the user wants to create shapes using CREATESSHAPE instead of shape, they
   can modify that in the property file and change their input text file according to
   that). Thus, I have tried to make the application as much user configurable as possible.

#############################################################################################


