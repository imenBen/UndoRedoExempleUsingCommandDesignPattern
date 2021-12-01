public class Command2 implements Command {

    private String name;

    public Command2(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Executing Action 2");
    }

    @Override
    public void undo() {
        System.out.println("Undo Action 2");
    }

    @Override
    public String getName() {
        return this.name;
    }


}