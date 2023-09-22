package foo;

import java.util.Scanner;


enum Status {
	Offline, Online, Busy
}

class Profile {
    public String name;
    public Status status;
    public AList<String> friendsList;
    
    public Profile() {
        name = "";
        status = Status.Offline;
        friendsList = new AList<String>();
    }

    public Profile (String userName, Status stat)
    {
        name = userName;
        status = stat;
        friendsList = new AList<String>();
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String newName) {
    	name = newName;
    }
    
    public Status getStatus() {
    	return status;
    }
    
    public void setStatus(Status stat) {
    	status = stat;
    }
    
    public AList<String> getFriends()
    {
        return friendsList;
    }
    
    public void setFriends(AList<String> list)
    {
    	friendsList = list;
    }

    public void addFriend(String friendName) {
        if (!friendsList.contains(friendName)) {
            friendsList.add(friendName);
        }
    }

    public void removeFriend(String friendName) {
    	for (int i = 1; i <= friendsList.getLength(); i++) {
    		if (friendsList.getEntry(i).equals(friendName)) {
    			friendsList.remove(i);
    			break;
    		}
    	}
    }
    
    public void displayFriendsList() {
        if (friendsList.getLength() == 0) {
        	System.out.println("Friends: No friends");
        }
        else {
        	System.out.println("Friends:");
        	for (int i = 1; i <= friendsList.getLength(); i++) {
        		if (i > 1) {
        			System.out.print(",");
        		}
        		System.out.print(" " + friendsList.getEntry(i));
        		if (i % 20 == 0) {
        			System.out.println();
        		}
        	}
        	System.out.println();
        }
    }
    
    public void displayStatus() {
    	System.out.println("\nName: " + name 
        		+ "\nStatus: " + status);
    }
    
    public void display() {
    	displayStatus();
    	displayFriendsList();    
    }
    
    public void create() {
    	readUserName();
    	readStatus();
    	readFriends();
    }
    
    public void readFriends() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Enter friend names, one name per line, or empty line to end:");
    	while (true) {
        	String friendName = input.nextLine();
        	if(friendName.isEmpty()) {
        		break;
        	}
        	friendsList.add(friendName);
    	}
    }
    
    public void deleteAllFriends() {
    	friendsList.clear();
    }
    public void readUserName() {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter username: ");
    	name = input.nextLine();
    }
    
    public void readStatus() {
    	boolean invalid = true;
    	Scanner input = new Scanner(System.in);
    	while (invalid) {
    		System.out.print("Enter status (online, offline, busy): ");
	    	String stat = input.nextLine();
	    	invalid = false;
	    	if (stat.equals("online")) {
	    		status = Status.Online;	
	    	}
	    	else if (stat.equals("offline")) {
	    		status = Status.Offline;
	    	}
	    	else if (stat.equals("busy")) {
	    		status = Status.Busy;
	    	}
	    	else {
	    		System.out.println("Invalid. Try again: ");
	    		invalid = true;
	    	}
    	}
    }
    
    public String readFriendName() {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter friend name: ");
    	return input.nextLine();
    }	
}

class Network {
	private HashedDictionary<String, Profile> users;
	
	public Network() {
		users = new HashedDictionary<String, Profile>();
	}

    public void create(Profile user) {
        users.add(user.getName(), user);
    }
    
    public Profile read(String userName)
    {
      return users.getValue(userName);
    }
    
    public void update(Profile user) {
    	create(user);
    }
    
    public void update(String userName, AList<String> friendsList) {
    	Profile user = read(userName);
    	user.setFriends(friendsList);
    	
    }

    public void delete(String userName) {
    	if (users.contains(userName)) {
        	users.remove(userName);
    		System.out.println("User " + userName + " is deleted from network");
    	}
    	else {
    		System.out.println("User not found in network");
    	}
    }

    public void removeFriendFromProfile(Profile user1, Profile user2)
    {
    	Profile firstUser = read(user1.getName());
    	Profile secondUser = read(user2.getName());
    	if (firstUser != null) {
    		firstUser.removeFriend(user2.getName());
    	}
    	if (secondUser != null) {
    		secondUser.removeFriend(user1.getName());
    	}
    }

    public void display(Profile user)
    {
    	user.display();
    }
    
    public void displayAllUsers() {
    	System.out.println("List of all users:");
    	if (users.getSize() == 0) {
    		System.out.println("No users found");
    	}
    	else {
    		users.displayValidEntries();
    	}
    }
    
    public void joinNetwork(Profile userProf) {
    	create(userProf);
    }
    
    public void leaveNetwork(String userName) {
    	delete(userName);
    }
    
    public Profile getProfile(String userName) {
    	return read(userName);
    }
    
    public void modifyProfile(String userName, Status status) {
    	Profile user = read(userName);
    	user.setStatus(status);
    }
    
    public void modifyProfile(String userName, AList<String> friendsList) {
    	Profile user = read(userName);
    	user.setFriends(friendsList);
    }
    
    public void addFriend(String userName, String friendName) {
    	Profile user = read(userName);
    	user.getFriends().add(friendName);
    }
    
    public void display(String userName) {
    	read(userName).display();
    }
    
    public void deleteFriendFromProfile(String friendName, String user)
    {
    	getProfile(user).removeFriend(friendName);
    }
}

public class SocialNetwork {
    public static void main(String[] args) {
    	System.out.println("*** Welcome to Social Network CIS22C ***\n");
    	
    	Network network = new Network();
        
    	 String option;
        int optionNum = 0;
        final int OPTION_QUIT = 11;
        
        while (optionNum != OPTION_QUIT) {
	        System.out.println("\nOptions:");
	        System.out.println("   1: Create a profile to join the network");
	        System.out.println("   2: Display full profile");
	        System.out.println("   3: Display status of a profile");
	        System.out.println("   4: Display friends list of a profile");
	        System.out.println("   5: Display all users of the network");
	        System.out.println("   6: Leave network (Delete a profile)");
	        System.out.println("   7: Modify the status of a profile");
	        System.out.println("   8: Add a friend to a profile");
	        System.out.println("   9: Delete a friend from a profile");
	        System.out.println("   10: Delete all friends from a profile");
	        System.out.println("   11: Quit");
            System.out.print("Enter your option: ");
            {
            	Scanner input = new Scanner(System.in);
            	option = input.nextLine();
            }
            
            try {
            	optionNum = Integer.parseInt(option);
            }
            catch(Exception e) {
            	System.out.println("Invalid option: " + e);
            }
            
            if (optionNum < 1 && optionNum > 11) {
            	System.out.println("Invalid option, try again");
            	continue;
            }
        	Profile prof = new Profile();
        	Status status;
            String username;
            String friendName;
            switch(optionNum) {
            case 1:
            	prof.create();
            	network.joinNetwork(prof);
            	break;
            case 2:
            	username = readUserName();
            	network.getProfile(username).display();
            	break;
            case 3:
            	username = readUserName();
            	network.getProfile(username).displayStatus();
            	break;
            case 4:
            	username = readUserName();
            	network.getProfile(username).displayFriendsList();
            	break;
            case 5:
            	network.displayAllUsers();
            	break;
            case 6:
            	username = readUserName();
            	network.leaveNetwork(username);
            	break;
            case 7:
            	username = readUserName();
            	status = readStatus();
            	prof = network.read(username);
            	prof.setStatus(status);
            	break;
            case 8:
            	username = readUserName();
            	friendName = readFriendName();
               	prof = network.read(username);
               	prof.getFriends().add(friendName);
            	break;
            case 9:
            	username = readUserName();
            	friendName = readFriendName();
               	prof = network.read(username);
            	prof.removeFriend(friendName);
            	break;
            case 10:
            	username = readUserName();
            	prof = network.read(username);
            	prof.deleteAllFriends();
            	break;
            case 11:
            	System.out.println("Bye");
            	break;
            default:
            	break;
            }
        }
    }
    
    public static String readUserName() {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter username: ");
    	String name = input.nextLine();
    	return name;
    }
    
    public static Status readStatus() {
    	Status status = Status.Offline;
    	boolean invalid = true;
    	Scanner input = new Scanner(System.in);
    	while (invalid) {
    		System.out.print("Enter status (online, offline, busy): ");
	    	String stat = input.nextLine();
	    	invalid = false;
	    	if (stat.equals("online")) {
	    		status = Status.Online;	
	    	}
	    	else if (stat.equals("offline")) {
	    		status = Status.Offline;
	    	}
	    	else if (stat.equals("busy")) {
	    		status = Status.Busy;
	    	}
	    	else {
	    		System.out.println("Invalid. Try again: ");
	    		invalid = true;
	    	}
    	}
    	return status;
    }
    
    public static String readFriendName() {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter friend name: ");
    	String name = input.nextLine();
    	return name;
    }	
}