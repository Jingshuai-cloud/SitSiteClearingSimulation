import java.util.ArrayList;

public class Trainee {
    ArrayList<String> commands = new ArrayList<String>();

    public void printAllCommand(){
        System.out.println("These are the commands you issued:");
        for(String command: this.commands){
            System.out.print(command);
            System.out.print(", ");
        }
    }

    public int caculateCommandCost(){
        int count = 0;
        for(String command: commands){
            if(!(command.equals("Wrong command") || command.equals("quit"))){
                count++;
            }
        }
        return count;
    }

    public void  addCommand(String command){
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

    private boolean isPositiveNumber(String input) {
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
