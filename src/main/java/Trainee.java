import java.util.ArrayList;

public class Trainee {
    //commands store all the user input command
    ArrayList<String> commands = new ArrayList<String>();

    //print all command
    public void printAllCommand(){
        System.out.println("These are the commands you issued:");
        for(String command: this.commands){
            System.out.print(command);
            System.out.print(", ");
        }
    }
    //transfer user input abbrev to full command
    public void  transferCommand(String command){
        //if user want to advance step
        String step = "";
        if(isPositiveNumber(command)){
            step = command;
            command = "a";
        }
        switch(command){
            case "a":
                this.commands.add("advance "+ step);
                break;
            case "l":
                this.commands.add("turn left");
                break;
            case "r":
                this.commands.add("turn right");
                break;
            case "q":
                this.commands.add("quit");;
                break;
            default:
                this.commands.add("Wrong command");
        }
    }

    //if input is a positive number, transfer command to advance when the input is positive
    public boolean isPositiveNumber(String input) {
        int step = -1;
        try
        {
            step = Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return step > 0;
    }
}
