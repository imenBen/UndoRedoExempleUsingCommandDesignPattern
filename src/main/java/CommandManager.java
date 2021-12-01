import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

class CommandManager {
    private static CommandManager instance = null;
    private Stack<List<Command>> stackNormal;
    private Stack<List<Command>> stackReverse;

    private List<String> actionHistory;

    static CommandManager getInstance(){
        if(instance != null)
            return instance;
        return new CommandManager();
    }

    private CommandManager() {
        stackNormal = new Stack<>();
        stackReverse = new Stack<>();
        actionHistory = new ArrayList<>();
    }

    void execute(List<Command> actionList){
        actionList.forEach(Command::execute);
        stackNormal.push(actionList);
        actionList.forEach(a -> actionHistory.add(a.getName()));
    }

    void undo() {
        Optional<List<Command>> optionalActions = Optional.of(stackNormal.pop());
        optionalActions.ifPresent(aList -> {
            aList.forEach(Command::undo);
            stackReverse.push(aList);
            aList.forEach(a -> actionHistory.add(a.getName() + " - undo"));
        });
    }

    void redo() {
        Optional<List<Command>> optionalActions = Optional.of(stackReverse.pop());
        optionalActions.ifPresent(aList -> {
            aList.forEach(Command::execute);
            stackNormal.push(aList);
            aList.forEach(a -> actionHistory.add(a.getName() + " - redo"));
        });
    }

    void clearNormal() {
        stackNormal.clear();
    }

    void clearReverse() {
        stackReverse.clear();
    }

    List<String> getActionHistory() {
        return actionHistory;
    }
    
    public static void main(String[] args) {
      CommandManager manager = CommandManager.getInstance();
      List<Command> actionList = new ArrayList<>();
      System.out.println("===ACTIONS===");
      
      actionList.add(new Command1("Action 1"));
      actionList.add(new Command2("Action 2"));
      
    
      manager.execute(actionList);

      manager.undo();
      manager.redo();

      
      manager.undo();
      manager.redo();

      System.out.println("===HISTORY===");
      System.out.println(manager.getActionHistory().toString());
    }


}
