/**
 * ENSF 409 Winter 2021 Final Group Project
 * Program designed with the intention of allowing a sustainable
 * supply chain furniture company to provide methods of purchasing
 * reconstructed or refurbished furniture items from their catalog, at the
 * lowest price possible for each item.
 *
 * @author <a href="christopher.chan2@ucalgary.ca>Christopher Chan</a>
 * @author <a href="amnah.hussain@ucalgary.ca>Amnah Hussain</a>
 * @author <a href="brooke.kindleman@ucalgary.ca>Brooke Kindleman</a>
 * @author <a href="neeraj.sunikumar@ucalgary.ca>Neeraj Sunil Kumar</a>
 *
 * @since 1.0
 *
 * @version 3.2
 */

package edu.ucalgary.ensf409;


/**
 * Class Main manages the start up and shut down of the program,
 * and contains the main method responsible for running the program.
 */
public class Main {

    /**
     * Main method for the program. Uses an infinite loop for calling
     * the UserIO input menu method, which provides interaction with
     * the user.
     * Loop will continue to run until the user opts to end the program
     * through a selection in the menu, after which the main method will
     * end the program.
     *
     * @param args Unused inputs from the command line when program
     *             is executed.
     */
    public static void main(String[] args) {
        boolean continueLoop = true;
        UserIO input = new UserIO();
        input.firstMenu();
        int selection = input.menu();
        continueLoop = input.processInput(selection);
        while(continueLoop) { // Will continue to run the program through
                                // the UserIO menu until the user opts to end
                                // the program and continueLoop is set as false.
            selection = input.menu();
            continueLoop = input.processInput(selection);

        }
        System.exit(0);

    }
}
