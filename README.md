# worker-management-java
This project using the MVC design pattern and UDP Socket

* Server:
  * Incluce worker information about: Fullname, Date of birth, City, Gender and Coefficient Salary.
  * Records stored in 2 table: Worker and City. The relationship between City and Worker is 1 - N (A city may have many worker).

* Client: Have an interface to implement function:
  - [x] Add new worker to database. The interface has a combobox for user to select City.
  - [x] Search worker by their name.
  - [x] List worker by their city.

> Requirement:
>> Create Worker class and establish table on database MySQL for above requirements.
>> Create methods of Server and Client to perform above requirements.
>> Install and run the requirements successfully: Add worker, Search worker, List worker.

>Tips:
>> When initializing, the Client needs to send a request to the server to get the list of provinces and add it to the Select List on the Client's interface.
>> Build a message class (Message) with data storage properties and message type distinguishing properties
