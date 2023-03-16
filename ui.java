package database;

import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener; 
import java.sql.SQLException;

	/**
	 * This class operates as the graphical user interface that interacts with the 
	 * Book and BookShelf classes to add Books to the array and return 
	 * a graphical representation of information from the BookShelf methods.  
	 * 
	 * @author  Tony O'Keeffe
	 * @version 1.0
	 */

public class BookGUI extends JFrame implements ActionListener
{
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	// Declare the instance variables for the class
	private String title  = "";
	private String author  = "";
	private String publisher  = "";
	private int year = 0;
	private double cost = 0;
    private boolean goodInput = false;
	
	// Define the exact dimensions of the graphical interface
    private static final int WIDTH = 600;
   	private static final int HEIGHT = 90;
//	private BookShelf bookShelf;
   	private BookDatabase bookStore;
//  Hello World


/**
  @wi.implements MATL-771 Java code as sample
*/
    public static void main(String[] args)
    {
       BookGUI gui = new BookGUI( );
       gui.setVisible(true);
    }
     
	 
	 /**
	  @wi.implements MATL-535 UI function
	  */
	  
    /**
     * Create the GUI frame with buttons and listeners
     */
    public BookGUI( )
    {
        // Create a new instance of BookShelf	
//    	bookShelf = new BookShelf();
    	bookStore = new BookDatabase();
    	
        JFrame frame = this; 
     	
        // Create the main frame's menu bar.
        JMenuBar menubar = new JMenuBar();
    	frame.setJMenuBar(menubar);
    	frame.pack();
    	frame.setVisible(true);
    	
    	//  Create the File menu
    	JMenu fileMenu = new JMenu("File");
    	menubar.add(fileMenu);
    	
    	// Create a Quit option on the File menu
    	JMenuItem quitItem = new JMenuItem("Quit");
    	fileMenu.add(quitItem);
    	quitItem.addActionListener(this); 
    	
    	// create the About menu
    	JMenu aboutMenu = new JMenu ("About");
    	menubar.add(aboutMenu);
    	
    	JMenuItem infoItem = new JMenuItem("Info");
    	aboutMenu.add(infoItem);
    	infoItem.addActionListener(this);
    	
    	//Set the Size of the frame    	
    	setSize(WIDTH, HEIGHT);
      
    	
        Container content = getContentPane( );

        
        // Arrange the buttons on the GUI in a Flow Layout
        content.setLayout(new FlowLayout());
             
        // Set up the required buttons on the GUI and the corresponding listener for that action.
        JButton button1 = new JButton("Add Book");
        content.add(button1);
        button1.addActionListener(this); 
        
        JButton button2 = new JButton("Cost of BookShelf");
        content.add(button2);
        button2.addActionListener(this);
        
        JButton button3 = new JButton("Size of BookShelf");
        content.add(button3);
        button3.addActionListener(this);
        
        JButton button4 = new JButton("Highest Price Paid");
        content.add(button4);
        button4.addActionListener(this);
        
        }
    
   /**
    * These methods perform the corresponding action when the buttons on the GUI are clicked.
    * Exception handling in these methods ensure the correct values are entered.  If not, the 
    * user is prompted to re-enter the data correctly.
    * 
    * @param e holds the event that was fired.
    */
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getActionCommand().equals("Add Book"))
        {    
    	   Book book = new Book("", "", 0, "", 0);
    	   title = JOptionPane.showInputDialog("Title");
           author = JOptionPane.showInputDialog("Author");
           publisher = JOptionPane.showInputDialog("Publisher");
        
           do{
              try 
              { 
        	     cost = Double.parseDouble(JOptionPane.showInputDialog("Cost"));
        	     book.setCost(cost);
        	     goodInput = true;
        	  } 
              catch (Exception cE){          
                 JOptionPane.showMessageDialog(this, "Numerical entry required. Please re-enter a value for cost");
        	  } 
           }while (!goodInput);

  	       goodInput = false;
           do{
               try 
               { 
         	     year = Integer.parseInt(JOptionPane.showInputDialog("Year"));
         	     book.setYear(year);
         	     goodInput = true;
         	  } 
               catch (Exception yE){          
                  JOptionPane.showMessageDialog(this, "Numerical entry required. Please re-enter a value for year");
         	  } 
            }while (!goodInput);
           
            //Add an instance of book to the ArrayList
            
    	    book.setTitle(title);
    	    book.setAuthor(author);
    	    book.setPublisher(publisher);
//    	    bookShelf.addBook(book);
            bookStore.saveBook(book);
        	
            String message =  "The Title of the book is " + book.getTitle() 
                           +  " the Author of the Book is "  + book.getAuthor()
                           + " it's published by " + book.getPublisher() 
                           + " in " + book.getYear()
                           + " and it costs " + book.getCost() + " euro ";
            JOptionPane.showMessageDialog(null, message, "Book Details", JOptionPane.PLAIN_MESSAGE);
        }
        else if (e.getActionCommand().equals("Size of BookShelf")) 
        {
           String message = "The book shelf has " + bookStore.getBookStoreSize() + " book(s)";
           JOptionPane.showMessageDialog(this, message);
        }
        else if (e.getActionCommand().equals("Cost of BookShelf")) 
        {
/**           String message;

			message = "The book shelf value is " + bookStore.getBookStoreTotalCost() + "Euro";
			JOptionPane.showMessageDialog(this, message);
*/
        	System.out.println(bookStore.getBookStoreTotalCost());
        }
    	else if (e.getActionCommand().equals("Info"))
        {
        	JOptionPane.showMessageDialog(this, "Pawel Makulski - GUI Assignment Two");
        }
        else if (e.getActionCommand().equals("Highest Price Paid"))
        {          
            String message = "The highest price paid for a book is: " + bookStore.getMaxPrice() + " EUR.";
            JOptionPane.showMessageDialog(this, message);
        } 
        else
        {
            System.exit( 0 );
        }
    	
     } // end actionPerformed

} //end BookGUI class
