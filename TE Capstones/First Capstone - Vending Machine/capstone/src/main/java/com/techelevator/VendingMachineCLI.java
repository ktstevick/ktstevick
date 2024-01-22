package com.techelevator;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

// @TODO: clean up the logging code
public class VendingMachineCLI {

	private static final int MAXIMUM_STOCK = 5;

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_HIDDEN_OPTION_GENERATE_SALES_REPORT = "*Generate Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_HIDDEN_OPTION_GENERATE_SALES_REPORT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	// Penny math
	public int currentMoney = 0;

	private Scanner userInput;

	// Necessary file references
	private static File inventoryFile = new File("vendingmachine.csv");
	private static File logFile = new File ("Log.txt");

	// List for internal method calls
	private static List<Item> inventoryList = new ArrayList<>();

	// Map for user display and presentation. GOATed TreeMap keeps things relatively ordered
	private static Map<String, Integer> displayMap = new TreeMap<>();

	// Assigns the starting menu
	private String[] currentMenu = MAIN_MENU_OPTIONS;

	// For menu navigation
	private String selection = "";

	// Vending machine Constructor
	public VendingMachineCLI() {
		userInput = new Scanner(System.in);
	}

	public void run() {

		boolean runMenu = true;

		while (runMenu) {

			displayMenu(currentMenu);

			System.out.print("\nPlease make a selection: ");
			selection = userInput.nextLine();

			try {
				int selectionIndex = Integer.parseInt(selection) - 1;

				String menuOption = currentMenu[selectionIndex];

				String logText = "";

				if (menuOption.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					System.out.println();
					System.out.println("Our humble offerings include and are limited to - ");
					System.out.println();

					// A for-each loop seems ideal for the displayMap
					for (String mapKey : displayMap.keySet()) {
						String displayLine = (mapKey + displayMap.get(mapKey));
						System.out.println(displayLine);
					}
				}
				if (menuOption.equals(MAIN_MENU_OPTION_PURCHASE)) {
						currentMenu = PURCHASE_MENU_OPTIONS;
				}
				if (menuOption.equals(MAIN_MENU_OPTION_EXIT)) {
					runMenu = false; //Terminate While Loop
					System.out.println("- * - Umbrella Corps. thanks you... we'll be in touch. - * -"); // TODO: UPDATE THIS DEFAULT EXIT ROUTINE PLACEHOLDER RESPONSE
				}
				if (menuOption.equals(MAIN_MENU_HIDDEN_OPTION_GENERATE_SALES_REPORT)) {

					String localTime = (LocalTime.now()).getHour() + "-" + (LocalTime.now()).getMinute() + "-" + (LocalTime.now()).getSecond();
					String reportFilePath = "salesreports/" + LocalDate.now() + "-at-" + localTime + "salesreport.txt";

					File newSalesReport = new File(reportFilePath);

					// PrintWriter to the report file
					try	(PrintWriter pr = new PrintWriter(newSalesReport)) {
						double totalSales = 0.0;

						for (String mapKey : displayMap.keySet()) {

							String[] itemData = mapKey.split("\\|");

							String itemName = itemData[1];
							double itemCost = Double.parseDouble(itemData[2]);
							int itemSold = (MAXIMUM_STOCK - displayMap.get(mapKey));

							totalSales += (itemCost * itemSold);

							pr.println(itemName + "|" + itemSold);
						}

						pr.printf("\n**TOTAL SALES** $%.2f", totalSales);
					}

					// Line for proof
					System.out.println("Sales report has been generated. Please check associated file.");
				}

				// Purchase Menu conditionals
				if (menuOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					System.out.println("Insert whole dollars: ");

					int addedMoney = (Integer.parseInt(userInput.nextLine()) * 100);

					if (addedMoney <= 0) {
						System.out.println("Sorry, positive whole dollars only. Please try again.");
					}

					if (addedMoney >= 1) {
						currentMoney += addedMoney;

						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
						String dateString = dateFormat.format(new Date());

						logText = (dateString + " FEED MONEY: $" + String.format("%.2f", ((double)addedMoney / 100)) + " $" + String.format("%.2f", ((double)currentMoney / 100)));

						try(FileWriter logWriter = new FileWriter(logFile, true)) {
							// This is maybe more involved than it needs to be, but it seems to work fine.
							BufferedWriter br = new BufferedWriter(logWriter);
							PrintWriter pr = new PrintWriter(br);

							pr.println(logText);

							pr.flush();
							pr.close();
							br.flush();
							br.close();
						}
					}
				}
				if (menuOption.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

					// Prints the list of available products and the amount remaining
					for (String mapKey : displayMap.keySet()) {
						String displayLine = (mapKey + displayMap.get(mapKey));
						System.out.println(displayLine);
					}

					System.out.println("Please make a selection: ");

					// This is all of the product selection logic
					String userKey = userInput.nextLine();
					String keySelection = "";

					for (String mapKey : displayMap.keySet()) {
						// Search loop for input key
						if (mapKey.contains(userKey)) {
							keySelection = mapKey;
						}
					}

					if (displayMap.containsKey(keySelection)) {

						int vendedItemIndex = 0;

						if (displayMap.get(keySelection) > 0) {
							boolean canAfford = true;
							// This checks to make sure there's enough money to buy the product

							// This finds the item we need to manipulate
							for (Item currentItem : inventoryList) {
								if (currentItem.getKeyCode().equals(userKey)) {
									vendedItemIndex = inventoryList.indexOf(currentItem);
									if ((int) ((inventoryList.get(vendedItemIndex).getPrice()) * 100) > currentMoney) {
										canAfford = false;
										System.out.printf("Sorry, that costs $%.2f", inventoryList.get(vendedItemIndex).getPrice());
										System.out.println(" - please 1) Feed Money");
									}
									break;
								}
							}
							if (canAfford) {
								System.out.println(inventoryList.get(vendedItemIndex).getFlavorText());

								int itemCost = (int) ((inventoryList.get(vendedItemIndex).getPrice()) * 100);
								currentMoney -= itemCost;

								// Updating Map value
								Integer newMapCount = displayMap.get(keySelection) - 1;
								displayMap.put(keySelection, newMapCount);

								// Log
								DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
								String dateString = dateFormat.format(new Date());

								logText = (dateString + " " + inventoryList.get(vendedItemIndex).getName() + " " + inventoryList.get(vendedItemIndex).getKeyCode()) + " $" + inventoryList.get(vendedItemIndex).getPrice() + " $" + String.format("%.2f", ((double) currentMoney / 100));

								try (FileWriter logWriter = new FileWriter(logFile, true)) {

									BufferedWriter br = new BufferedWriter(logWriter);
									PrintWriter pr = new PrintWriter(br);

									pr.println(logText);

									pr.flush();
									pr.close();
									br.flush();
									br.close();
								}

								// Permanently remove item
								inventoryList.remove(vendedItemIndex);
							}
						}else {
							System.out.println("That item is sold out! Please make a different selection.");
						}
					} else {
						System.out.println("Not a valid selection: Please try again!");
					}
				}
				if (menuOption.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

					currentMenu = MAIN_MENU_OPTIONS;

					int changeProvided = currentMoney;

					int quarterCount = changeProvided / 25;
					int changeRemainderAfterQuarters = changeProvided % 25;

					int dimeCount = changeRemainderAfterQuarters / 10;
					int changeRemainderAfterDimes = changeRemainderAfterQuarters % 10;

					int nickelCount = changeRemainderAfterDimes / 5;

					System.out.printf("Change provided: $%.2f", ((double)changeProvided / 100));
					System.out.println(" - (" + quarterCount + ") Quarters, (" + dimeCount + ") Dimes, and (" + nickelCount + ") Nickels were emailed to you.");

					currentMoney = 0;

					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
					String dateString = dateFormat.format(new Date());

					logText = (dateString + " GIVE CHANGE: $" + String.format("%.2f", ((double)changeProvided / 100)) + " $" + String.format("%.2f", ((double)currentMoney / 100)));

					try(FileWriter logWriter = new FileWriter(logFile, true)) {

						BufferedWriter br = new BufferedWriter(logWriter);
						PrintWriter pr = new PrintWriter(br);

						pr.println(logText);

						pr.flush();
						pr.close();
						br.flush();
						br.close();
					}
				}

				// This is where we catch the exceptions for both menus
			}catch (NumberFormatException e){
				System.out.println("Sorry, whole dollars only. Change is provided, but not accepted.");
			}catch (FileNotFoundException e) {
				/* This happens when the log file can't be reached. We don't want the consumer to know we are not logging,
				(To discourage theft) so no message is displayed. If this kind of thing happened with a real vending machine,
				it may behoove us to simply say it's out of order.
				*/
			}catch (IOException e){
				//This is if the output stream does not close. Similarly, we don't want to bring this to the consumer's attention
			}catch (Exception e){
				System.out.printf("'%s' Is Not a Valid Option%n", selection);
			}
		}
	}

	// Menu display formatting
	private void displayMenu(String[] menu){

		System.out.println("\n********************************");

		// This is where we need to make the formatting for whole dollar amount PLUS CHANGE
		if (currentMenu.equals(PURCHASE_MENU_OPTIONS)){
			double displayMoney = ((double)currentMoney / 100);
			System.out.printf("Current money provided: $%.2f", displayMoney);
			System.out.println("\n");
		}

		for(int i = 0; i < menu.length; i++){
			if( ! menu[i].startsWith("*")) {
				System.out.printf("%1$s) %2$s\n", i + 1, menu[i]);
			}
		}
		System.out.println("********************************");
	}

	public static void main(String[] args) {

		// try-with-resources to "fill the vending machine"
		try(Scanner inventoryReader = new Scanner(inventoryFile)){

			while (inventoryReader.hasNextLine()){
				String currentLine = inventoryReader.nextLine();
				String[] itemProperties = currentLine.split("\\|");

				if (itemProperties[3].equals("Chip")){
					// For loop that creates 5 bags of THIS type of chips
					for(int i = 0; i < MAXIMUM_STOCK; i++){
						Chip newChip = new Chip(itemProperties[0], itemProperties[1], Double.parseDouble(itemProperties[2]));
						inventoryList.add(newChip);

						// Recombining String array for presentation purposes
						String mapKey = (itemProperties[0] + "|" + itemProperties[1] + "|" + itemProperties[2] + "|Left: ");
						int mapValue = (i + 1);

						// Updating Map
						displayMap.put(mapKey, mapValue);
					}
				}
				if (itemProperties[3].equals("Candy")){
					for(int i = 0; i < MAXIMUM_STOCK; i++){
						Candy newCandy = new Candy(itemProperties[0], itemProperties[1], Double.parseDouble(itemProperties[2]));
						inventoryList.add(newCandy);

						String mapKey = (itemProperties[0] + "|" + itemProperties[1] + "|" + itemProperties[2] + "|Left: ");
						int mapValue = (i + 1);

						displayMap.put(mapKey, mapValue);
					}
				}
				if (itemProperties[3].equals("Drink")){
					for(int i = 0; i < MAXIMUM_STOCK; i++){
						Drink newDrink = new Drink(itemProperties[0], itemProperties[1], Double.parseDouble(itemProperties[2]));
						inventoryList.add(newDrink);

						String mapKey = (itemProperties[0] + "|" + itemProperties[1] + "|" + itemProperties[2] + "|Left: ");
						int mapValue = (i + 1);

						displayMap.put(mapKey, mapValue);
					}
				}
				if (itemProperties[3].equals("Gum")){
					for(int i = 0; i < MAXIMUM_STOCK; i++){
						Gum newGum = new Gum(itemProperties[0], itemProperties[1], Double.parseDouble(itemProperties[2]));
						inventoryList.add(newGum);

						String mapKey = (itemProperties[0] + "|" + itemProperties[1] + "|" + itemProperties[2] + "|Left: ");
						int mapValue = (i + 1);

						displayMap.put(mapKey, mapValue);
					}
				}
				/* This Miscellaneous class exists just in case something is being sold that there isn't a pre-built class for.
				I'd like to include a note explaining how to add classes, but not every vending machine owner is going to have
				the know-how to add a class themselves!
				 */
				else {
					for(int i = 0; i < MAXIMUM_STOCK; i++){
						Miscellaneous newItem = new Miscellaneous(itemProperties[0], itemProperties[1], Double.parseDouble(itemProperties[2]));
						inventoryList.add(newItem);

						String mapKey = (itemProperties[0] + "|" + itemProperties[1] + "|" + itemProperties[2] + "|Left: ");
						int mapValue = (i + 1);

						displayMap.put(mapKey, mapValue);
					}
				}
			}

		}catch(FileNotFoundException e){
			System.out.println("No inventory file found. Please try again later.");
		}catch (Exception e){
			System.out.println("Something went wrong checking the inventory. Please try again later.");
		}

		// Legacy code, instantiates VendingMachine and runs
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}



























