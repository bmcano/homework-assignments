# Instructions

Python version: `3.9.13`

Libraries used: `neo4j`

Editor: Visual Studio Code

- First run the neo4j locolhost server with the tables already create from the queries in Homework7
- Then at the top of the `connection.py` there is a `PASSWORD` variable, change that to the password of your database
- Run the file in the terminal with the command `python connection.py` - need to be in the directory to run it.
- A console interface will be prompted with all the options available and a exit command, the display will reappear everytime after an option has been fully executed.
- If there is a connection issue with the program, it will exit.
- If the user enteres an invalid command or query item the program will explain that their was an invalid input and the likely reason for it.

The sample_output.txt file shows how the interactins works with the cases of invalid inputs as well and how it handles them, along with showing the objects retrieved after the queries are ran.