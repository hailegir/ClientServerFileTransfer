/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver.client;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hai
 */
public class Client extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private Socket clientSocket;
    private static final boolean DEBUG = false;
    private static final String File_Line_Header = "#HEADER_V_1#";
    private List<String> arrayOfWords;

    /**
     * Creates new form ClientGUI
     */
    public Client(String host, int port) {
        try {
            this.clientSocket = new Socket(host, port);
            this.arrayOfWords = new ArrayList<>();
        } catch (UnknownHostException e) {
            LOGGER.log(Level.SEVERE, "Exception occured, Host unknown: " + host, e);
            System.exit(1);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception occured, Couldn't get I/O for " + "the connection to: " + host + ".", e);
            System.exit(1);
        }
        //Initialize the GUI components
        initComponents();
    }
    
    //Main method
    public static void main(String args[]) {
        Client clientGUI = new Client("localhost", 4444);
        clientGUI.setVisible(true);
        clientGUI.processResponseThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        getFileTextArea = new javax.swing.JTextArea();
        scrollPane2 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        top10WordsTextArea = new javax.swing.JTextArea();
        getFileButton = new javax.swing.JButton();
        getTop10WordsButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setForeground(new java.awt.Color(204, 204, 255));
        
        setTitle("Client GUI ");
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Client Application");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Send file request to server");

        getFileTextArea.setEditable(false);
        getFileTextArea.setColumns(20);
        getFileTextArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getFileTextArea.setRows(5);
        getFileTextArea.setText("\tText File Recieved from Server\n\n");
        add(getFileTextArea, BorderLayout.CENTER);
        jScrollPane2.setViewportView(getFileTextArea);

        scrollPane1.add(jScrollPane2);

        top10WordsTextArea.setEditable(false);
        top10WordsTextArea.setColumns(20);
        top10WordsTextArea.setRows(5);
        top10WordsTextArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        top10WordsTextArea.setText("Rank\tWord\tOccurence\n\n");
        add(top10WordsTextArea, BorderLayout.CENTER);
        jScrollPane1.setViewportView(top10WordsTextArea);

        scrollPane2.add(jScrollPane1);

        getFileButton.setText("Get File");
        getFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFileButtonActionPerformed(evt);
            }
        });

        getTop10WordsButton.setText("Top 10");
        getTop10WordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTop10WordsButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("List Top 10 Words in the file");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(getFileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(getTop10WordsButton)
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(224, 224, 224))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getFileButton)
                    .addComponent(getTop10WordsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    public void getFileButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        //Request the server to send file
        String request = "#SendFile#";
        getFileTextArea.setText("\tText File Recieved from Server\n\n");
        arrayOfWords = new ArrayList<>();
        sendRequest(request);
    }                                             

    public void getTop10WordsButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    

    	top10WordsTextArea.setText("Rank\tWord\tOccurence\n\n");

        List<String> uniqueListOfWords = null;
        if(arrayOfWords != null && !arrayOfWords.isEmpty()){
            uniqueListOfWords = new ArrayList<>(new HashSet<>(arrayOfWords));
        }

        Map<String, Integer> wordFrequencypair = getWordOccurencePair(uniqueListOfWords);

        List<Map.Entry<String, Integer>> list = new LinkedList<>(wordFrequencypair.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        
        Map<String, Integer> result = new LinkedHashMap<>();
        if(DEBUG)System.out.println("Sorted List...");
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
            if(DEBUG)System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        List<Map.Entry<String, Integer>> sortedMapEntry = new LinkedList<>(result.entrySet());
        if(DEBUG)System.out.println("Top 10 List of words from Entry...");
        int no = 1;
        for (int i = sortedMapEntry.size() - 1; i > sortedMapEntry.size() - 11; i--) {
            String stringFormat = " " + no + "\t"+ sortedMapEntry.get(i).getKey() + "\t    " + sortedMapEntry.get(i).getValue();
            top10WordsTextArea.append( stringFormat + "\n");
            if(DEBUG)System.out.println(stringFormat);
            no++;
        }

    }                                                   
    
    private Map<String, Integer> getWordOccurencePair(List<String> uniqueWords) {
        Map<String, Integer> wordFrequencypair = null;
        if (uniqueWords != null && !uniqueWords.isEmpty()) {
            wordFrequencypair = new HashMap<>();
            for (int i = 0; i < uniqueWords.size(); i++) {
                int occurence = 0;
                occurence = Collections.frequency(arrayOfWords, uniqueWords.get(i));
                String word = uniqueWords.get(i).trim();
                if (!word.isEmpty() && word != null) {
                    wordFrequencypair.put(word, occurence);
                    if(DEBUG)System.out.println("ID-" + i + " = " + word + " : " + occurence);
                }
            }
        }

        return wordFrequencypair;
    }
              
    void sendRequest(String request) {
        try {
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            out.println(request);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception occured at Client Socket while sending request to server: ", e);
            System.exit(1);
        }
    }

    public Thread processResponseThread = new Thread() {
        void processResponse() {
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ArrayList<String> arrayFile = new ArrayList<>();
                String msg;
                while ((msg = rd.readLine()) != null) {
                    System.out.println("INFO : Msg Recieved from Server : " + msg);
                    if (msg.contains(File_Line_Header)) {
                        msg = msg.replaceFirst(File_Line_Header, "");
                        arrayFile.add(msg);
                        getFileTextArea.append("\t" + msg + "\n");

                        //Get the list of words
                        String[] words = msg.split(" ");
                        for (String word : words) {
                            if (word != null) {
                                if (word.contains(",")) {
                                    word = word.replaceAll(",", "");
                                }
                                word = word.trim();
                                if(DEBUG)System.out.print(word + "-");
                                if (word != null && !word.isEmpty()) {
                                    arrayOfWords.add(word.toLowerCase());
                                    if(DEBUG)System.out.print(word + "\t");
                                }
                            }                            
                        }
                    }

                }
                if(DEBUG){
                   for (String s : arrayFile) {
                        System.out.println("#Received File#  :" + s);
                    } 
                }

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "IO Exception occured while reading from Client Socket: ", e);
                System.exit(1);
            }finally{
                try{
                   clientSocket.close(); 
                }catch(IOException e){
                    LOGGER.log(Level.SEVERE, "Exception occured, Client Socket cannot be closed: ", e);
                }
            }
        }

        public void run() {
            processResponse();
        }
    };


    // Variables declaration - do not modify                     
    private javax.swing.JButton getFileButton;
    private javax.swing.JTextArea getFileTextArea;
    private javax.swing.JButton getTop10WordsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.ScrollPane scrollPane1;
    private java.awt.ScrollPane scrollPane2;
    private javax.swing.JTextArea top10WordsTextArea;
    // End of variables declaration                   
}
