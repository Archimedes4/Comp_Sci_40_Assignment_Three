/*
  Andrew Mainella
  Ms. Latimer
  Third Assignment
  Level: Gold
  Caesar Cypher
  HandedIn : 10 November 2023
*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;

class Assignment_Three {
  public static String check = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-_=+`~{}[]|:;<,>.?/' "; //Ceaser cypher
  public static Integer offset = 10;
  public static String encrypt(String message) {
    String[] messageArray = message.split(""); //Convet to array makes it easier to handle
    String encryptedString = ""; //result
    for (Integer index = 0; index < messageArray.length; index++) { //Iterate through
      Integer foundIndex = check.indexOf(messageArray[index]); //find current position
      if (foundIndex != -1) { //Check to make sure exists in cypher. If not invalid so just ignores.
        if ((foundIndex + offset) < check.length()) { //Check that new position valid
        encryptedString += check.charAt(foundIndex + offset); //add new char
      } else {
        encryptedString += check.charAt(foundIndex + offset - check.length()); //add new char
      }
      }
    }
    return encryptedString;
  }
  public static String decrypt(String message) {
    String[] messageArray = message.split("");//Convet to array makes it easier to handle
    String decodedString = ""; //result
    for (Integer index = 0; index < messageArray.length; index++) { //Iterate through
      Integer foundIndex = check.indexOf(messageArray[index]); //find current position
      if (foundIndex != -1) {//Check to make sure exists in cypher. If not invalid so just ignores.
        if ((foundIndex - offset) > -1) { //Check that new position valid
          decodedString += check.charAt(foundIndex - offset); //add new char
        } else {
          decodedString += check.charAt(foundIndex - offset + check.length()); //add new char
        }
      }
    }
    return decodedString;
  }
  public static void main(String[] args) {
    //Frame
    JFrame frame = new JFrame();
    frame.setSize(300, 400);

    //Message Text box
    JTextField messageTextField = new JTextField("enter the text", 16);
    messageTextField.setBounds(0, 0, 300, 150);
    frame.add(messageTextField);

    //Encrypt button
    JButton encryptButton= new JButton("Encrypt");
    encryptButton.addActionListener(new ActionListener(){  
      //On Press
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        messageTextField.setText(encrypt(messageTextField.getText()));
      }  
    });
    encryptButton.setBounds(0, 150, 150, 100);
    frame.add(encryptButton);

    //Decrypt button
    JButton decryptButton= new JButton("Decrypt");
    decryptButton.addActionListener(new ActionListener(){  
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        messageTextField.setText(decrypt(messageTextField.getText()));
      }  
    });
    decryptButton.setBounds(150, 150, 150, 100);
    frame.add(decryptButton);

    //Offset label holds current offset
    JLabel offsetJLabel = new JLabel("Offset 10");
    offsetJLabel.setBounds(0, 250, 300, 20);
    frame.add(offsetJLabel);

    //Offset Text Box
    JTextField offsetTextField = new JTextField("10", 4);
    offsetTextField.setBounds(0, 270, 300, 50);
    frame.add(offsetTextField);

    //Offset Button
    JButton offsetButton = new JButton("Change Offset");
    offsetButton.addActionListener(new ActionListener(){  
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        try {
          //Gettting new text will fail if not integer and revert to old offset
          Integer value = Integer.parseInt(offsetTextField.getText());
          offset = value;

          //Check for rollover
          if (offset < 0) {
            offset = offset%-check.length();
          } 
          if (offset > check.length()) {
            offset = offset%check.length();
          }
        } catch (Exception error) {
          //No need to error hanel
        }
        //set new offset in label
        offsetJLabel.setText("Offset " + offset.toString());
      }  
    });
    offsetButton.setBounds(0, 320, 300, 50);
    frame.add(offsetButton);

    //Containter to make sure things look good
    JPanel container = new JPanel();
    frame.add(container);
    frame.setVisible(true);
  }
}