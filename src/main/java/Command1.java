public class Command1 implements Command {

    private String name;

    public Command1(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Executing Action 1");
    }

    @Override
    public void undo() {
        System.out.println("Undo Action 1");
    }

    @Override
    public String getName() {
        return this.name;
    }


}