import java.util.Scanner;

public class test
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Features network = new Features();
        while (true) {
        	display();
        	int choice = Integer.parseInt(input.nextLine());
        	if (choice == 0)
        		break;
        	else if (choice == 1)
        			network.addProfile();
        	else if (choice == 2)
        			network.removeProfile();
        	else if (choice == 3)
        			network.searchProfile();
        	else if (choice == 4)
        			network.modifyProfile();
        	else if (choice == 5)
        			network.displayFriends();
        }
    }
        	

    private static void display() {
        System.out.print("Choose an option:\n" +
                         "1. Create profile\n" +
                         "2. Delete profile\n" +
                         "3. Search profile\n" +
                         "4. Modify profile\n" +
                         "5. Display friends list\n" + 
                         "0. Log out\n");
    }
}

    class Features {
        private AList<Profile> user;

        public Features() {
           user = new AList<Profile>();
        }

        public void addProfile() {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter name of profile: ");
            String name = input.nextLine();
            Profile m = new Profile(name);
            user.add(m);
        }
          
        public void removeProfile() {
           Scanner input = new Scanner(System.in);
           System.out.println("Enter name: ");
           String name = input.nextLine();
           
           int found = 0;
           for (int i = 1; i <= user.getLength(); i++) {
        	   if (user.getEntry(i).getName().equals(user)) {
        		   user.remove(i);
        		   found = 1;
        	   }
        	   else
        		   System.out.println("Not found");
           }
           }

       public void searchProfile() {
    	   Scanner input = new Scanner(System.in);
    	   System.out.println("Enter name: ");
    	   String name = input.nextLine();
    	   int found = 0;
    	   for (int i = 1; i <= user.getLength(); i++) {
    		   if (user.getEntry(i).getName().equals(user)) {
    			   System.out.println("Name: " + name);
    			   System.out.println("Status: " + user.getEntry(i).getStatus());
    			   found = 1;
    		   }
    		   else
        		   System.out.println("Not found");
    	   }
       }
       
       public void modifyProfile() {
    	   Scanner input = new Scanner(System.in);
    	   System.out.println("Enter name: ");
    	   String name = input.nextLine();
    	   int found = 0;
    	   for (int i = 1; i <= user.getLength(); i++) {
    		   if (user.getEntry(i).getName().equals(name)) {
    			   System.out.println("Enter new name: ");
    			   name = input.nextLine();
    			   user.getEntry(i).setName(name);
    			   found = 1;
    		   }
    		   else
        		   System.out.println("Not found");
    	   }
       }
       
       public void displayFriends() {
    	   Scanner input = new Scanner(System.in);
    	   System.out.println("Enter name: ");
    	   String name = input.nextLine();
    	   int found = 0;
    	   for (int i = 1; i <= user.getLength(); i++) {
    		   if (user.getEntry(i).getName().equals(name)) {
    			   user.getEntry(i).displayFriendList();
    			   found = 1;
    		   }
    		   else
        		   System.out.println("Not found");
    	   }
       }

    }
    
    class Profile {
        private String name;
        private String status;
        private Profile next;

        public Profile(String n) {
        	name = n;
            status = "Active";
            next = null;
        }


        String getStatus()
        {
            return status;
        }

        public void setStatus(String a){
            status = a;
        }

        String getName()
        {
            return name;
        }

        public void setName(String a)
        {
            name = a;
        }

        public void addFriends(String a) {
            Profile m = new Profile(a);
            
            if (next == null) {
            	next = m;
            }
            else {
            	Profile p = next;
            	while (p.next != null)
            		p = p.next;
            		p.next = m;
            }
        }


        public void displayFriendList() {
           if (next == null) {
        	   System.out.println("No friends");
           }
           else {
        	   Profile p = next;
        	   while (p != null) {
        		   System.out.println(p.getName());
        		   p = p.next;
        	   }
           }
        }
    }