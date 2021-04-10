# Supply Chain Manager

----------------------------------------------------------------------
### ENSF 409 Final Group Project - Winter 2021
### Group 22



> #### Members:
>* Christopher Chan - [christopher.chan2@ucalgary.ca](christopher.chan2@ucalgary.ca) 
>* Amnah Hussain - [amnah.hussain@ucalgary.ca](amnah.hussain@ucalgary.ca)
>* Brooke Kindleman - [brooke.kindleman@ucalgary.ca](brooke.kindleman@ucalgary.ca)   
>* Neeraj Sunil Kumar - [neeraj.sunikumar@ucalgary.ca](neeraj.sunikumar@ucalgary.ca)

> #### Relevant links
> TO_FILL_IN

----------------------------------------------------------------------

## About the Program
*Supply Chain Manager* is a program intended to provide user-friendly system
for managing a sustainable furniture supply chain 
that reconfigures pieces of old furniture into recycled items for a low cost.

By using a database of individual components, their manufacturers, 
cost, and the multiple configurations,  all possible combinations for creating a
complete furniture item can be evaluated. This functionality, allows the 
program to accurately determine the most cost-efficient way to assemble 
furniture that fulfills the user's requests.

If a configuration is not possible, the *Supply Chain Manager* will supply
the user with a list of alternate suppliers who may be able to fulfill
their request for furniture with new items. 

In addition to managing the database for the supply chain and providing
the most cost-efficient way for a piece of furniture to be manufactured,
*Supply Chain Manager* provides a way for the user to save seperate forms for
each successful order for later reference or offline distribution.

All together, these features allow *Supply Chain Manager* to be an 
easy-to-use, helpful program for making sustainable furniture more accessible
than ever.
----------------------------------------------------------------------
----------------------------------------------------------------------

>***Note: Compilation and Execution instructions are provided for operation on 
Windows operating systems.
Other operating systems will need to modify these instructions slightly.***

----------------------------------------------------------------------

## Compilation Instructions for *Supply Chain Manager*
1. Enter the directory `"SupplyChainManager/"` in the command line

2. Ensure the database to be used for management of items is accesible by the 
current machine.

3. Ensure the following directories are contained within the current 
   directory:
    1. `"edu/ucalgary/ensf409/"`
    2. `"lib/"`

4. Using the command line, compile the *Supply Chain Manager* source code using 
   the command
   
    >`javac -cp .;lib/* edu/ucalgary/ensf409/*.java`

----------------------------------------------------------------------    

## Execution Instructions for *Supply Chain Manager*
1. Enter the directory `"SupplyChainManager/"` in the command line
2. Ensure the *Supply Chain Manager* program has been compiled correctly
3. Ensure the following directories are contained within the current
   directory:
    1. `"edu/ucalgary/ensf409/"`
    2. `"lib/"`
    
4. Using the command line, execute the *Supply Chain Manager* program using the
command 
   
   >`java -cp .;lib/* edu.ucalgary.ensf409.Main`
   
5. Follow the directions provided in the terminal to operate 
   the *Supply Chain Manager*. 
---------------------------------------------------------------------
---------------------------------------------------------------------
## Compilation Instructions for *Supply Chain Manager* Unit Testing
* Follow *Compilation Instructions for *Supply Chain Manager**, above.
---------------------------------------------------------------------
## Execution Instructions for *Supply Chain Manager* Unit Testing
1. Enter the directory `"SupplyChainManager/"` in the command line
2. Ensure the *Supply Chain Manager* program has been compiled correctly
3. Ensure the following directories are contained within the current 
   directory:
    1. `"edu/ucalgary/ensf409/"`
    2. `"lib/"`

4. Using the command line, execute the *Supply Chain Manager* program using the
   command

   >`java -cp .;lib/* org.junit.runner.JUnitCore 
   > edu.ucalgary.ensf409.SupplyChainTest`
   > 
---------------------------------------------------------------------
