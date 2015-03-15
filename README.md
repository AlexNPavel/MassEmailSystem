# MassEmailSystem
A system to set up mass emails using 2 text files. NOTE: Different OSes store data in different formats. If using linux, use the standard UTF-8 format, and if in Windows, save as "ANSI" (otherwise known CP1252). Other formats and OSes have not yet been tested.

The names of the people being emailed is stored in "emails.txt" in the standard format uploaded in this repository.
The message is stored in "messages.txt". The first line is the subject, and every line after is the message. To enter the recipient's name in the message, just type "recipient_name", and the program will automatically use the information from "emails.txt" to substitute the correct name.
