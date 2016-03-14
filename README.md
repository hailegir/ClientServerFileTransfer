# ClientServerFileTransfer
This is a simple socket-based Client Server file transfer Application in Java

# Basic functionality
   - the server fetches the islands_in_the_stream.txt file from disk
   - the server sends the file to the client up on request
   - the client displays the file
   - the client displays the top 10 words in the file up on request

# How to compile and run

The best way to compile and run would be to import the project in to Netbean or Eclipse IDE
and run it from there. 

I have already included client.jar and server.jar files in Executables folder which 
is in the main project (ClientServerFileTransfer) folder.

Steps to compile and run the project

1. Download the project to your computer

2. Import the project to Eclipse or NetBeans IDE
   - Import from Zip for Netbeans
   - Import project from Folder after extracting the downloaded zip project file for Eclipse

3. Run the Server

4. Run the Client  
   
5. Click on getFile button on the client GUI opened

	- you will see the text file sent from the Server on the GUI

6. Then click the getTop10 button
	
	- you will see the top 10 words in the file with their frequency of occurrence
	
You can run multiple clients because each client runs in a new thread

Thanks for your time!

