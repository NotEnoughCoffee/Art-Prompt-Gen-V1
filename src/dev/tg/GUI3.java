package dev.tg;

// Daily Art Theme Generator
// Created By: Andrew Sparks
// Current Release Version: v1.0
// Release Date: 25April2024
// -------------------------
// Program not authorized for resale or commercial use.
// -------------------------
// Description:
// This program creates a random daily art challenge for furry artists.

// Planned Additions:
// - More Themes and choices
// - Additional Roll Type (Hard Challenge)
// - Customization Menu to give users options for all settings
//      - This should open a secondary window with checkboxes
//      - Also includes buttons to apply and save settings.
//      - Additional Roll Type for Custom Roll settings
// - Potentially upgrade to using comma delimited lists via text files to provide changeable theme lists
// - Potentially add NSFW themes, made toggleable
// - Reconsider balancing of elements and adjust weighted rolls

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GUI3 extends JPanel
                    implements ActionListener {
    protected JTextPane textArea;
    //protected String nextLine = "\n";
    protected Action ChallengeButtonAction, RollButtonAction, SaveButtonAction;
    //protected JCheckBoxMenuItem[] checkBoxMenuItems;
    protected static JFrame frame;
    protected boolean simpleChallenge = false;
    protected JButton challengeButton;

    public GUI3(){
        super(new BorderLayout());

        textArea = new JTextPane();
        StyledDocument documentStyle = textArea.getStyledDocument();
        SimpleAttributeSet centered = new SimpleAttributeSet();
        StyleConstants.setAlignment(centered, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centered, false);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        textArea.setFont(new Font("Ariel",Font.BOLD,24));

        JScrollPane scrollPane = new JScrollPane(textArea);

        setPreferredSize(new Dimension(600, 400));
        add(scrollPane, BorderLayout.CENTER);

        ChallengeButtonAction = new ChallengeButtonAction(
                    "Daily Challenge",
                    getButtonIcon("null"),
                    "Change Challenge Type",
                    KeyEvent.VK_SPACE);

        RollButtonAction = new RollButtonAction(
                "Roll!",
                getButtonIcon("null"),
                "Roll Challenge!",
                KeyEvent.VK_ENTER);

        SaveButtonAction = new SaveButtonAction(
                "Save",
                getButtonIcon("null"),
                "Save Challenge!",
                KeyEvent.VK_U);
    }
    protected static ImageIcon getButtonIcon(String imageFileName){
        String imageLocation = "/UtilityGraphics/toolbar/"
                                + imageFileName
                                + ".gif";
        //alter filepath when file system established, for now all return null
        java.net.URL imageURL = GUI3.class.getResource(imageLocation);

    if (imageURL != null) {
        return new ImageIcon(imageURL);
    } else {
        System.err.println("Unable to locate resource at: " + imageLocation);
        return null;
    }
}
    protected void createToolBar(){
        JButton button = null;
        JButton saveButton = null;

        JToolBar toolBar = new JToolBar();
        add(toolBar,BorderLayout.PAGE_START);

        challengeButton = new JButton(ChallengeButtonAction);
        if(challengeButton.getIcon() != null){
            challengeButton.setText("");
        }
        toolBar.add(challengeButton);

        button = new JButton(RollButtonAction);
        if(button.getIcon() != null){
            button.setText("");
        }
        toolBar.add(button);

        saveButton = new JButton(SaveButtonAction);
        if(saveButton.getIcon() != null) {
            saveButton.setText("");
        }
        toolBar.add(saveButton);
    }
    private static void createGUI(){
        //frame.dispose();
        frame = new JFrame("Drawing Challenge Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI3 createGUI = new GUI3();
        createGUI.createToolBar();
        createGUI.setOpaque(true);
        frame.setContentPane(createGUI);

        frame.pack();
        frame.setVisible(true);
    }
    public void displayResult(ActionEvent e){
        String results;
        if(simpleChallenge){
            results = Rolls.simpleChallengeRollGUI();
        }else results = Rolls.dailyChallengeRollGUI();
        textArea.setText(results);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //
        System.exit(0);
    }

    public class ChallengeButtonAction extends AbstractAction {

        private String buttonText;
        public ChallengeButtonAction(String buttonText, ImageIcon icon, String description, Integer mnemonic){
            super(buttonText,icon);
            putValue(SHORT_DESCRIPTION, description);
            putValue(MNEMONIC_KEY, mnemonic);
            this.buttonText = buttonText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //rebuilds the frame to change the challenge button?
            if (Objects.equals(challengeButton.getText(), "Daily Challenge")) {
                simpleChallenge = true;
                challengeButton.setText("Simple Challenge");

            } else if (Objects.equals(challengeButton.getText(), "Simple Challenge")) {
                simpleChallenge = false;
                challengeButton.setText("Daily Challenge");
            }
        }
    }
    public class SaveButtonAction extends AbstractAction {

        public SaveButtonAction(String buttonText, ImageIcon icon, String description, Integer mnemonic){
            super(buttonText,icon);
            putValue(SHORT_DESCRIPTION, description);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                saveImage(captureWindow(textArea));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public class RollButtonAction extends AbstractAction {

        public RollButtonAction(String buttonText, ImageIcon icon, String description, Integer mnemonic){
            super(buttonText,icon);
            putValue(SHORT_DESCRIPTION, description);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            displayResult(e);
        }

    }

    public void saveImage(BufferedImage image) {

        try {
            String date = String.valueOf(System.currentTimeMillis());
            File outputfile = new File("screenshots/challenge_capture_" + date + ".png");
            ImageIO.write(image, "png", outputfile);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public BufferedImage captureWindow(JComponent component) throws IOException {
        BufferedImage captureWindow = new BufferedImage(component.getSize().width, component.getSize().height, BufferedImage.TYPE_INT_RGB);
        component.paint(captureWindow.createGraphics());
        return captureWindow;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
}