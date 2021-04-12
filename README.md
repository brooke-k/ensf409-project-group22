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
> [Presentation Video](https://ucalgary.yuja.com/V/Video?v=333094&node=1207031&a=1023996715&autoplay=1)


*All components of the project (i.e. source code, extra libraries, and) are included within
the submitted zip file, with the exception of the presentation video.*

*The presentation video can be found in an independant zip file within the same dropbox, 
or at the link above.*

*Copies of certain tests, including those for classes DatabaseIO and PriceOptimizer, 
are located in separate files to allow for tests to be run on those classes as isolated instances. 
They are intended to be used for debugging and finetuning certain individual classes.*

*All unit tests for 'Supply Chain Manager', including tests which can also be found in 
other test files, are contained in "SupplyChainTest.java", to allow for all 81 total
tests to be run in one operation.*

----------------------------------------------------------------------

## About the Program
*Supply Chain Manager* is a program intended to provide a user-friendly system
for managing a sustainable furniture supply chain 
that reconfigures pieces of old furniture into new recycled furniture for a low cost.

By using a database of individual components, their manufacturers, 
cost, and the status of their components, the program evaluates all
possible configurations buying the used furniture. This functionality allows the 
program to accurately determine the most cost-efficient way to assemble 
refurbished furniture items that fulfill the user's requests.

If a configuration is not possible, the *Supply Chain Manager* will supply
the user with a list of alternate suppliers who may be able to fulfill
their request for furniture with new items based on the information in the
database.

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

## Compilation Instructions for *Supply Chain Manager* and All *Supply Chain Manager* Unit Tests
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
