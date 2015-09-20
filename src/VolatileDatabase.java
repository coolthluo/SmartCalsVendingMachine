//concrete database: a volatile database that uses java list to store all the data
import java.util.ArrayList;
import java.util.HashSet;

public class VolatileDatabase implements DBManager {
	
	private ArrayList<Item> itemList;
	private ArrayList<Machine> machineList;
	private ArrayList<MachineItem> machineItemList;
	private ArrayList<Sale> salesList;
	private ArrayList<Employee> employeeList;
	private ArrayList<Card> cardList;
	
	public VolatileDatabase(){
		itemList = new ArrayList<>();
		machineList = new ArrayList<>();
		machineItemList = new ArrayList<>();
		salesList = new ArrayList<>();
		employeeList = new ArrayList<>();
		cardList = new ArrayList<>();
		itemList.add(new Item(1, "Coke", 1, "drink", 182, 44, "itemsinfo/item_1.html", 
				"itemspic/item_1.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(2, "Sprite", 1, "drink", 192, 44, "itemsinfo/item_2.html", 
				"itemspic/item_2.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(3, "Lemonade", 1.5, "drink", 99, 25, "itemsinfo/item_3.html", 
				"itemspic/item_3.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(4, "Orange juice", 1.5, "drink", 39, 7, "itemsinfo/item_4.html", 
				"itemspic/item_4.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(5, "Water", 1, "drink", 0, 0, "itemsinfo/item_5.html", 
				"itemspic/item_5.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(6, "Diet coke", 1, "drink", 1, 0, "itemsinfo/item_6.html", 
				"itemspic/item_6.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(7, "Oreo", 1, "snack", 270, 23, "itemsinfo/item_7.html", 
				"itemspic/item_7.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(8, "Candy", 1, "snack", 234, 24, "itemsinfo/item_8.html", 
				"itemspic/item_8.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(9, "Chips", 1, "snack", 160, 1, "itemsinfo/item_9.html", 
				"itemspic/item_9.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(10, "Energy bar", 1, "snack", 235, 22, "itemsinfo/item_10.html", 
				"itemspic/item_10.jpeg", "2015-07-30 00:00:00"));
		itemList.add(new Item(11, "Chocolate", 1, "snack", 155, 14, "itemsinfo/item_11.html", 
				"itemspic/item_11.jpeg", "2015-07-30 00:00:00"));
		employeeList.add(new Employee(1, "Sam", "one"));
		employeeList.add(new Employee(2, "Tom", "two"));
		
	}
	
	public ArrayList<Item> getAllItems(){
		return itemList;
	}
	
	public ArrayList<Item> getOtherItems(int machine){
		ArrayList<Item> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for(MachineItem machineItem: machineItemList){
			if(machineItem.getMachineId() == machine){
				set.add(machineItem.getItemId());
			}
		}
		for(Item item: itemList){
			if(!set.contains(item.getID())){
				list.add(item);
			}
		}
		return list;
	}
	
	public Item getItem(int code){
		for(Item item: itemList){
			if(item.getID() == code){
				return item;
			}
		}
		return null;
	}
	
	public Employee getEmployee(int code){
		for(Employee employee: employeeList){
			if(employee.getID() == code){
				return employee;
			}
		}
		return null;
	}
	
	public int addMachine(int code, String address, String startdate, String lastsync){
		for(Machine machine: machineList){
			if(machine.getID() == code){
				return -1;
			}
		}
		machineList.add(new Machine(code, address, startdate, lastsync));
		return code;
	}
	
	public int checkMachine(int code){
		for(Machine machine: machineList){
			if(machine.getID() == code){
				return 1;
			}
		}
		return 0;
	}
	
	public void updateMachineSyncDate(int code, String date){
		for(Machine machine: machineList){
			if(machine.getID() == code){
				machine.setSyncDate(date);
			}
		}
	}
	
	public void addItemToMachine(int machine, int item, int capacity, int quantity){
		machineItemList.add(new MachineItem(machine, item, capacity, quantity));
	}
	
	public void deleteItemFromMachine(int machine, int item){
		for(MachineItem machineItem: machineItemList){
			if(machineItem.getMachineId() == machine && machineItem.getItemId() == item){
				machineItemList.remove(machineItem);
				return;
			}
		}
	}
	
	public void updateMachineItemQuantity(int machine, int item, int quantity){
		for(MachineItem machineItem: machineItemList){
			if(machineItem.getMachineId() == machine && machineItem.getItemId() == item){
				machineItem.setQuantity(quantity);
				return;
			}
		}
	}
	
	public ArrayList<Item> getUpdatedItems(int code){
		String date = null;
		for(Machine machine: machineList){
			if(machine.getID() == code){
				date = machine.getLastSync();
				break;
			}
		}
		if (date == null){
			return itemList;
		}
		ArrayList<Item> list = new ArrayList<>();
		for(Item item: itemList){
			if(date.compareTo(item.getLastMod()) < 0){
				list.add(item);
			}
		}
		return list;
	}
	
	public void addSale(int machine, int item, double profit, String date){
		salesList.add(new Sale(machine, item, profit, date));
	}
	
	public int addCard(double balance){
		int code;
		if(cardList.size() == 0){
			code = 1;
		} else {
			code = cardList.get(cardList.size() - 1).getId() + 1;
		}
		cardList.add(new Card(code, balance));
		return code;
	}
	
	public double checkBalance(int code){
		for(Card card: cardList){
			if(card.getId() == code){
				return card.getBalance();
			}
		}
		return 0;
	}
	
	public double updateBalance(int code, double deduction){
		for(Card card: cardList){
			if(card.getId() == code){
				double oldBalance = card.getBalance();
				double newBalance = oldBalance - deduction;
				card.setBalance(newBalance);
				return card.getBalance();
			}
		}
		
		return -1;
	}
	
	public String getFile(String path) throws Exception {
		return path + " file";
	}

}
