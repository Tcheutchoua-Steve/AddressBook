This is a small addressbook application based on the requirement found on https://github.com/gumtreeuk/address-book 

The application is desktop application with Graphical User Interface built using JavaFx. 

The following operations are performed 
finding the oldest male in the CSV File 
Finding the number of males in the CSV File 
Getting the difference in days between the date of birth of two different people. 

This application can be found on github following the link 

A document called SampleShots.pdf contains some snapshots of the application. 

The application uses maven as build system. 

To run the application, perform the following operations 
1) mvn clean install
2) java -jar target/AddressBook-1.0-SNAPSHOT.jar 
3) if the data is not loaded , 
4) Click on the file menu, locate the csv vile and click open
5) Click on Operations on the menu and follow to perform other operations


Asumptions 
A CSV file can only be loaded once. To load a csv file, 
i) select the file menu on the tool bar 
ii) Browse thought the file manager or file explorer and choose the csv file you want.