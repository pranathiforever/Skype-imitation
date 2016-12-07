package skype;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
//import MediaPanel;
    
     public class MediaTest    {
       // launch the application

      public URL getUrl()
       {
           URL mediaURL = null;
          // create a file chooser
          JFileChooser fileChooser = new JFileChooser();
   
          // show open file dialog
         int result = fileChooser.showOpenDialog( null );
   
          if ( result == JFileChooser.APPROVE_OPTION ) // user chose a file
          {
             
   
             try
             {
               // get the file as URL
                mediaURL = fileChooser.getSelectedFile().toURL();
                System.out.println(mediaURL);
             } // end try
             catch ( MalformedURLException malformedURLException )
            {
                System.err.println( "Could not create URL for the file" );
            } // end catch
   
             if ( mediaURL != null ) // only display if there is a valid URL
             {
               System.out.println(mediaURL.toString());
             } // end inner if
          } // end outer if
          return mediaURL;
       } // end main

 
    } // end class MediaTest