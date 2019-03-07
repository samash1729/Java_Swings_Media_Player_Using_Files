

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javazoom.jl.decoder.JavaLayerException;
import javax.swing.DefaultListModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jl.player.Player;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish Samanta
 */
public class mp3gui extends javax.swing.JFrame {

    /**
     * Creates new form MP3GU
     */
  
    
    int sta, fin ;
    char[] chr = new char[50];
    public boolean playing = false ;
    public String univ_path = "";
    String[] coll = new String[100];
    int count = -1 ;
    public String final_path = "";
    public int index = 0 ;
    public boolean stopbool = false ;
    public Player player ;
    FileInputStream fis;
    BufferedInputStream bis;
    public long songtotallength;
    public long pauselocation ;
    public String fileloc;
    public boolean finish = false;
    public boolean shuffleon = false ;
    public boolean repeaton = false ;
    String[] playlistfiles = new String[10];
    int play_count = -1 ;
    int i = 0 ;
    
    public mp3gui() throws ClassNotFoundException {
        
        
        initComponents();
        
 
        this.setTitle("MUSIC PLAYER");			//Heading of App
        np.setText("NOW PLAYING : ");
        DocumentListener doclist;
        doclist = new DocumentListener()		//Used to Search For songs
        {
            @Override
            public void insertUpdate(DocumentEvent e) {
                
                stuff_changing(e);
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                
                stuff_changing(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
                stuff_changing(e);
                
            }
            public void stuff_changing(DocumentEvent doceve)
            {
                somelist.removeAll();
                if(!jtext.getText().equals(""))
                {
                for(int k=0;k<listnew.getItemCount();++k)
                {
                    if(listnew.getItem(k).contains(jtext.getText()))
                    {
                        somelist.add(listnew.getItem(k));
                    }
                }
                }
                    
            }
            
        };
        
        jtext.getDocument().addDocumentListener(doclist); 			//Adding the Document Listener to JText
        String path = "C:\\music";
        File folder = new File(path);
        File[] listfiles = folder.listFiles();
        for(int i=0;i<listfiles.length;++i)							//Searching a particular directory on start up and adding files
        {
            if(listfiles[i].isFile())
            {
                listnew.add(listfiles[i].getName());
                coll[++count] = "C:\\music\\"+listfiles[i].getName();
            }
        }
        ArrayList<String> arr = new ArrayList<String>();
        
        try
        {
            FileInputStream fis = new FileInputStream("D:\\sometext.txt");		//Using Files to store Playlist Data
            ObjectInputStream ois = new ObjectInputStream(fis);					//Serializing and storing in File
            arr = (ArrayList)ois.readObject();
            
            ois.close();
            fis.close();
        }
        catch(IOException p)
        {
            p.printStackTrace();
        }
        for(String tmp : arr)
        {
            char[] chr = new char[50];
            char[] res = new char[50];
            coll[++count] = tmp ;
            chr = tmp.toCharArray();
            int c = -1;
            for(int j=tmp.length()-1;chr[j]!='\\';--j)
            {
                res[++c] = chr[j];
            }
            StringBuilder build = new StringBuilder(String.valueOf(res));
            listnew.add(build.reverse().toString().trim());
            
            
        }
        System.out.println("***"+count+"***");
      
    }
    
    public void next_func() throws FileNotFoundException
    {
        stopit();
        if(repeaton == true)
        {
            try {
                playit(coll[index]);
            } catch (JavaLayerException ex) {
                Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else      
        {
            if(shuffleon == false)
            {
                index = (++index)%(count+1);
                try {
                    playit(coll[index]);
                } catch (JavaLayerException ex) {
                Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else
            {
                int randomnum = ThreadLocalRandom.current().nextInt(count+1);
                try
                {
                    playit(coll[randomnum]);
                }
                catch (JavaLayerException ex) {
                    Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp = new javax.swing.JPanel();
        play = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        listnew = new java.awt.List();
        add = new javax.swing.JButton();
        shuffle = new javax.swing.JButton();
        skip = new javax.swing.JButton();
        jtext = new javax.swing.JTextField();
        somelist = new java.awt.List();
        repeat = new javax.swing.JButton();
        setit = new javax.swing.JButton();
        playlist_button = new javax.swing.JButton();
        np = new javax.swing.JLabel();
        resume = new javax.swing.JButton();
        shuff = new javax.swing.JLabel();
        rep = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        play.setText("Play");
        play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playMouseClicked(evt);
            }
        });

        stop.setText("Stop");
        stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopMouseClicked(evt);
            }
        });

        pause.setText("pause");
        pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pauseMouseClicked(evt);
            }
        });

        listnew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listnewMouseClicked(evt);
            }
        });

        add.setText("ADD");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        shuffle.setText("SHUFFLE");
        shuffle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shuffleMouseClicked(evt);
            }
        });

        skip.setText("SKIP");
        skip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                skipMouseClicked(evt);
            }
        });

        somelist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                somelistMouseClicked(evt);
            }
        });
        somelist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                somelistActionPerformed(evt);
            }
        });

        repeat.setText("REPEAT");
        repeat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                repeatMouseClicked(evt);
            }
        });

        setit.setText("SETTINGS");
        setit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setitMouseClicked(evt);
            }
        });

        playlist_button.setText("PLAYLIST");
        playlist_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playlist_buttonMouseClicked(evt);
            }
        });
        playlist_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playlist_buttonActionPerformed(evt);
            }
        });

        np.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        np.setText("NOW PLAYING :");

        resume.setText("RESUME");
        resume.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resumeMouseClicked(evt);
            }
        });

        shuff.setText("SHUFFLE OFF");

        rep.setText("REPEAT OFF");

        jLabel1.setText("SEARCH SONGS");

        javax.swing.GroupLayout jpLayout = new javax.swing.GroupLayout(jp);
        jp.setLayout(jpLayout);
        jpLayout.setHorizontalGroup(
            jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpLayout.createSequentialGroup()
                        .addComponent(listnew, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtext)
                                    .addComponent(somelist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jpLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(setit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(repeat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(skip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(59, 59, 59))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpLayout.createSequentialGroup()
                        .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpLayout.createSequentialGroup()
                                .addComponent(add)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playlist_button)
                                .addGap(8, 8, 8)
                                .addComponent(shuffle)
                                .addGap(18, 18, 18)
                                .addComponent(shuff)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rep))
                            .addComponent(np, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpLayout.createSequentialGroup()
                                .addComponent(stop)
                                .addGap(58, 58, 58)
                                .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(resume)
                                .addGap(83, 83, 83)
                                .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        jpLayout.setVerticalGroup(
            jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLayout.createSequentialGroup()
                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stop)
                    .addComponent(play)
                    .addComponent(pause)
                    .addComponent(resume))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(np, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(1, 1, 1)
                        .addComponent(jtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(somelist, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repeat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(skip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(jpLayout.createSequentialGroup()
                        .addComponent(listnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)))
                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(shuffle)
                    .addComponent(playlist_button)
                    .addComponent(shuff)
                    .addComponent(rep))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playMouseClicked
        
        stopbool = false ;
        try {
            playit(final_path);
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_playMouseClicked

    private void stopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopMouseClicked
                stopit();

 
    }//GEN-LAST:event_stopMouseClicked

    private void pauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseMouseClicked
        pause();        // TODO add your handling code here:
    }//GEN-LAST:event_pauseMouseClicked

    private void listnewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listnewMouseClicked
        
        np.setText("NOW PLAYING : "+listnew.getSelectedItem());
        sta = evt.getSource().toString().indexOf("=");
        fin = evt.getSource().toString().indexOf("]");
        int c = -1;
        for(int u=0;u<50;++u)
        {
            chr[u]=' ';
        }
        for(int j=sta+1;j<fin;++j)
        {
            chr[++c] = evt.getSource().toString().charAt(j);
        }
        univ_path = String.valueOf(chr);
        String substr = univ_path.substring(0,5);
        System.out.println(substr);
        for(int h=0;h<=count;++h)
        {
            if(coll[h].contains(substr))
            {
                final_path = coll[h];
                index = h ;
                break;
            }
        }
      // System.out.println(univ_path);
    }//GEN-LAST:event_listnewMouseClicked

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        
        JFileChooser chooser = new JFileChooser("C:\\music");		//Adding Songs of a Directory using JChooser
        chooser.setDialogTitle("CHOOSE FILE");
        int returnval = chooser.showOpenDialog(null);
        ArrayList<String> al = new ArrayList<String>();
        if(returnval == JFileChooser.APPROVE_OPTION)
        {
            File myfile = chooser.getSelectedFile();
            String somepath = myfile+"";
            if(somepath.endsWith("mp3"))
            {
                try
                {
                    FileInputStream fis = new FileInputStream("D:\\sometext.txt");	//Storing Files here
                    ObjectInputStream ois = new ObjectInputStream(fis);
               
                        try {
                            al = (ArrayList)ois.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    ois.close();
                    fis.close();
                }
                catch(IOException r)
                {
                    r.printStackTrace();
                }
                al.add(somepath);
                char[] chr = new char[50];
                chr = somepath.toCharArray();
                char[] res = new char[50];
                int q=-1;
                for(int k=somepath.length()-1;chr[k]!='\\';--k)
                {
                    res[++q] = chr[k];
                }
                StringBuilder build = new StringBuilder(String.valueOf(res));
                listnew.add(build.reverse().toString().trim());
                try
                {
                    FileOutputStream fos = new FileOutputStream("D:\\sometext.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(al);
                    oos.close();
                    fos.close();
               
                }
                catch(IOException h)
                {
                    h.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_addMouseClicked

    private void shuffleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shuffleMouseClicked
        if(shuffleon == false)
        {
            shuffleon = true ;
            shuff.setText("SHUFFLE ON");
        }else
        {
            shuffleon = false;
            shuff.setText("SHUFFLE OFF");
        }
    }//GEN-LAST:event_shuffleMouseClicked

    private void skipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_skipMouseClicked
        try {
            next_func();        // TODO add your handling code here:
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_skipMouseClicked

    private void somelistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_somelistMouseClicked
          
         System.out.println(somelist.getSelectedItem());
         String substri = somelist.getSelectedItem().substring(0,5);
        for(int h=0;h<=count;++h)
        {
            if(coll[h].contains(substri))
            {
                final_path = coll[h];
                System.out.println(final_path);
                np.setText("NOW PLAYING : "+somelist.getSelectedItem().toString());
                index = h ;
                break;
            }
        }
    }//GEN-LAST:event_somelistMouseClicked

    private void repeatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repeatMouseClicked
        if(repeaton == true)
        {
            repeaton = false ;
            rep.setText("REPEAT OFF");
        }
        else
        {
            repeaton = true ;
            rep.setText("REPEAT ON");
        }
    }//GEN-LAST:event_repeatMouseClicked

    private void somelistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_somelistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_somelistActionPerformed

    private void setitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setitMouseClicked
        settings someit = new settings();
        someit.setVisible(true);
        someit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        someit.colourbox.addItem("BLACK");
        someit.colourbox.addItem("BLUE");
        someit.colourbox.addItem("GREEN");
        someit.colourbox.addItem("MAGENTA");
        someit.colourbox.addItem("GRAY");
        someit.colourbox.addItem("DARK GRAY");
        someit.colourbox.addItem("ORANGE");
        someit.faq.addItem("How to create a playlist ?");
        someit.faq.addItem("How to Add songs to library ?");
        someit.faq.addItem("How to Search For Songs ?");
        someit.faq.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                String str = someit.faq.getSelectedItem().toString();
                System.out.println(str);
                if(str.equals("How to create a playlist ?"))
                {
                    
                    someit.textarea.setText("On The main menu click on playlist button "+'\n'
                    +"another screen will appear where you can create a playlist and add songs to it");
                    
                }
                if(str.equals("How to Add songs to library ?"))
                {
                    someit.textarea.setText("Click on add button in main window a pop up menu"+'\n'+
                    "will appear where you can navigate to your required file");
                }
                if(str.equals("How to Search For Songs ?"))
                {
                    someit.textarea.setText("Start typing in the search bar and click on the required choice");
                }
            }
        });{
        
        
    }
                someit.colourbox.addItemListener(new ItemListener()	//Change Color of Music Player
                {
            @Override
            public void itemStateChanged(ItemEvent e) {
              
                if(ItemEvent.SELECTED==1)
                {
                     String str = someit.colourbox.getSelectedItem().toString();
                     if(str.equals("BLACK"))
                     {
                         jp.setBackground(Color.BLACK);
                     }
                     else if(str.equals("BLUE"))
                     {
                         jp.setBackground(Color.BLUE);
                     }
                     else if(str.equals("GREEN"))
                     {
                         jp.setBackground(Color.GREEN);
                     }
                     else if(str.equals("MAGENTA"))
                     {
                         jp.setBackground(Color.MAGENTA);
                     }
                     else if(str.equals("ORANGE"))
                     {
                         jp.setBackground(Color.orange);
                     }
                     else if(str.equals("GRAY"))
                     {
                         jp.setBackground(Color.gray);
                     }
                     else if(str.equals("DARK GRAY"))
                     {
                         jp.setBackground(Color.DARK_GRAY);
                     }
                     else
                     {
                         System.out.println("System Colour Error");
                     }
                }
            }
               
                });
                
                someit.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }//GEN-LAST:event_setitMouseClicked

    private void playlist_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_buttonMouseClicked
        playlistform plf = new playlistform();
        plf.setVisible(true);
        plf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        for(int j = 0;j<listnew.getItemCount();++j)
        {
            plf.mainlist.add(listnew.getItem(j));
        }
        String path = "D:\\playlist";
        File folder = new File(path);
        File[] listfiles = folder.listFiles();
        play_count = -1 ;
        for(int i=0;i<listfiles.length;++i)
        {
            if(listfiles[i].isFile())
            {
                plf.sublist.add(listfiles[i].getName());
                playlistfiles[++play_count] = "D:\\playlist\\"+listfiles[i].getName();
            }
        }
    }//GEN-LAST:event_playlist_buttonMouseClicked

    private void playlist_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playlist_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playlist_buttonActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void resumeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resumeMouseClicked
         try {
            fis = new FileInputStream(fileloc);
            bis = new BufferedInputStream(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            player = new Player(bis);
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        try {
            fis.skip(songtotallength - pauselocation);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        new Thread()
        {
            public void run()
            {
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                   ex.printStackTrace();
                }
            }
        }.start();
            
        
    }//GEN-LAST:event_resumeMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mp3gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mp3gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mp3gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mp3gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new mp3gui().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void playlistsongs(String path) throws FileNotFoundException
    {
        for(int j=0;j<=count;++j)
        {
            if(coll[j].contains(path))
            {
                try {
                    playit(coll[j]);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Playing Playlist");
                break;
            }
        }
    }
    public void playit(String path) throws FileNotFoundException, JavaLayerException
    {
       
        
        {
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            try {
                songtotallength = fis.available();
                fileloc = path+"";
                } catch (IOException ex) {
                ex.printStackTrace();
            }
            player = new Player(bis);
            new Thread()
            {
                public void run()
                {
                
                    finish = false ;
                    if(player!=null)
                    {
                        try {
                            player.play();
                        } catch (JavaLayerException ex) {
                            ex.printStackTrace();
                        }
                
                        if(player.isComplete())
                        {
                            
                            if(player == null)
                            {
                                try {
                                    next_func();
                                } catch (FileNotFoundException ex) {
                                Logger.getLogger(mp3gui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        
                             }
                            System.out.println("DONE");
                        }
                    }
                    else
                    {
                        System.out.println("One Song Already Playing ! ");
                    }
                
                }
            }.start();
        }
       
    }
   
    public void stopit()
    {
        if(player!=null)
        {
           
            player.close();
        }
    }
    
    public void pause()
    {
        if(player != null)
        {
            try {
                pauselocation = fis.available();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            player.close();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jp;
    private javax.swing.JTextField jtext;
    private java.awt.List listnew;
    private javax.swing.JLabel np;
    private javax.swing.JButton pause;
    private javax.swing.JButton play;
    private javax.swing.JButton playlist_button;
    private javax.swing.JLabel rep;
    private javax.swing.JButton repeat;
    private javax.swing.JButton resume;
    private javax.swing.JButton setit;
    private javax.swing.JLabel shuff;
    private javax.swing.JButton shuffle;
    private javax.swing.JButton skip;
    private java.awt.List somelist;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables
}
