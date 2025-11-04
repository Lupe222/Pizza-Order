/**Pizza Order Project
*@author Guadalupe Inamagua Morocho
*@version Fall 2025
*CSCI 1130
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Main extends JFrame implements ItemListener, ActionListener{
    Image img;
    final double TOPPING_COST = 1.99;
    final double SMALL_PRICE = 6.99;
    final double MEDIUM_PRICE = 11.99;
    final double LARGE_PRICE = 16.99;

    boolean isToppingEvent = false;
    int toppingsCount = 0;
    double initialPizzaPrice = 0;

    //String outputString = "";
 
    //JPanels
    JPanel checkPanel, outputPanel, sizePanel;

    //GUI components
    JCheckBox pepperoniBox, mushroomBox, cheeseBox;
    JRadioButton smallRadio, mediumRadio, largeRadio;
    JButton submitButton;
    ButtonGroup sizeGroup;
    

    //Output
    JTextArea outputArea;

   public static void main(String[] args){
        Main frame = new Main();
 
        frame.setLayout(new BorderLayout());
 
        frame.setupGUI();
 
        frame.pack();
 
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void loadImage(){
        String path = "files/Pizza.jpg"; 
        File file = new File(path);
        try {
        img = ImageIO.read(file);
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e){
        outputArea.append(buildOutputString());
    }

    public void itemStateChanged(ItemEvent e){
 
        //this whole thing will only execute if the source is the sausage box
        // if(e.getSource() == sausageBox || e.getSource() == onionBox || e.getSource() == pepperoniBox){
        //       isToppingEvent = true;
        // }
 
        isToppingEvent = e.getSource() == cheeseBox || e.getSource() == mushroomBox || e.getSource() == pepperoniBox;
        //isSizeEvent = e.getSource() == largeRadio
 
        if(isToppingEvent){
            if(e.getStateChange() == ItemEvent.SELECTED){
                toppingsCount++;
            } else{
                toppingsCount--;
            }
        }

        System.out.println("Total toppings: " + toppingsCount);
        System.out.println("Total price for toppings: " + toppingsCount * TOPPING_COST);
       
        if (smallRadio.isSelected()) initialPizzaPrice = SMALL_PRICE;
        else if (mediumRadio.isSelected()) initialPizzaPrice = MEDIUM_PRICE;
        else if (largeRadio.isSelected()) initialPizzaPrice = LARGE_PRICE;
    }

    public String buildOutputString(){
         String outputString = " - Pizza Order -\n";//local to the method

        outputString += "Toppings:\n";
        if(pepperoniBox.isSelected()){
            outputString += "  Pepperoni\n";
        }
        if(mushroomBox.isSelected()){
            outputString += "  Mushrooms\n";
        }
        if(cheeseBox.isSelected()){
            outputString += "  Extra Cheese\n";
        }
       
        if (smallRadio.isSelected()){
            outputString += "Size: Small $6.99\n";
        } 
        else if (mediumRadio.isSelected()){
            outputString += "Size: Medium $11.99\n";
        } 
        else if (largeRadio.isSelected()){
        outputString += "Size: Large $16.99\n";
        }

        outputString += "Total toppings: " + toppingsCount + "\n";
        outputString += "Total price for toppings: $" + toppingsCount * TOPPING_COST + "\n";
        outputString += "Pizza price: $" + initialPizzaPrice + "\n";
        outputString += "----------------------\n";
        outputString += "Total: $" + (initialPizzaPrice + (toppingsCount * TOPPING_COST)) + "\n";

        return outputString;
    }

    public void setupGUI(){
        setupGUIPanels();
        loadImage();
        //toppings
        pepperoniBox = new JCheckBox("PEPPERONI");
        checkPanel.add(pepperoniBox);
        pepperoniBox.addItemListener(this);
 
        mushroomBox = new JCheckBox("MUSHROOMS");
        checkPanel.add(mushroomBox);
        mushroomBox.addItemListener(this);
 
        cheeseBox = new JCheckBox("EXTRA CHEESE");
        checkPanel.add(cheeseBox);
        cheeseBox.addItemListener(this);

        submitButton = new JButton("SUBMIT");
        checkPanel.add(submitButton);
        submitButton.addActionListener(this);
 
        outputPanel.setLayout(new BorderLayout());

        outputArea = new JTextArea(20, 25);

        //text
        outputPanel.add(outputArea, BorderLayout.CENTER);

        //image
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        outputPanel.add(imageLabel, BorderLayout.EAST);

        //size
        sizeGroup = new ButtonGroup();
        sizePanel.add(new JLabel("Choose Size:"));
        
        smallRadio = new JRadioButton("SMALL");
        sizeGroup.add(smallRadio);
        sizePanel.add(smallRadio);
        smallRadio.addItemListener(this);

        mediumRadio = new JRadioButton("MEDIUM");
        sizeGroup.add(mediumRadio);
        sizePanel.add(mediumRadio);
        mediumRadio.addItemListener(this);

        largeRadio = new JRadioButton("LARGE");
        sizeGroup.add(largeRadio);
        sizePanel.add(largeRadio);
        largeRadio.addItemListener(this);
    }

    public void setupGUIPanels(){
        outputPanel = new JPanel();
        checkPanel = new JPanel(new GridLayout(1, 4));
        sizePanel = new JPanel(new FlowLayout());
        add(outputPanel, BorderLayout.SOUTH);
        add(checkPanel, BorderLayout.CENTER);
        add(sizePanel, BorderLayout.NORTH);
    }
}
