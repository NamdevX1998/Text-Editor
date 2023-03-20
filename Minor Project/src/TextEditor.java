import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newfile,open,save;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;

    TextEditor(){
        //initializing object
        frame =new JFrame("Text Editor");

        menuBar=new JMenuBar();

        file=new JMenu("File");

        edit=new JMenu("Edit");


        newfile=new JMenuItem("New File");
        open=new JMenuItem("Open File");
        save=new JMenuItem("Save File");
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        textArea=new JTextArea();

        //Attach action listner to every menuitems befoe adding these menuitems to specific menu.
        //this means object of text editor class.Now object of text editor class is behave like ActionListener.
        newfile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);
        selectAll.addActionListener(this);


        //add menu item to the menu
        file.add(newfile);
        file.add(open);
        file.add(save);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //add menu bar to frame
        frame.setJMenuBar(menuBar);

        //create content pannel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to pannel
        panel.add(scrollPane);
        //add pannel to frame
        frame.add(panel);


        //frame setup
        frame.setLocation(200,200);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setLayout(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(ae.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(ae.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(ae.getSource()==selectAll){
            //perform selectAll operation
            textArea.selectAll();
        }
        if(ae.getSource()==close){
            //perform close operation
            System.exit(0);
        }
        if(ae.getSource()==save){
            //initialize file picker
            JFileChooser fileChooser=new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    //initialize buffer writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();

                }
            }
        }
        if(ae.getSource()==newfile){
            TextEditor newtextEditor=new TextEditor();
        }
        if(ae.getSource()==open){
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file=fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath=file.getPath();
                try{
                    //initialize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //initialize buffer reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    //read contents of file line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}
