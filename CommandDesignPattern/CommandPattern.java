public class CommandPattern {
    public interface Command {
        public void execute();

        public void undo();
    }

    public static class Light {
        public void on() {
            System.out.println("Light is ON");
        }

        public void off() {
            System.out.println("Light is OFF");
        }
    }

    public static class Fan {
        public void start() {
            System.out.println("Fan is STARTED");
        }

        public void stop() {
            System.out.println("Fan is STOPPED");
        }
    }

    public static class LightCommand implements Command {
        private Light light;

        public LightCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.on();
        }

        @Override
        public void undo() {
            light.off();
        }
    }

    public static class FanCommand implements Command {
        private Fan fan;

        public FanCommand(Fan fan) {
            this.fan = fan;
        }

        @Override
        public void execute() {
            fan.start();
        }

        @Override
        public void undo() {
            fan.stop();
        }
    }


    public static class RemoteController {
        private final int numButtons = 4;
        private Command buttons[] = new Command[numButtons];
        private boolean buttonPressed[] = new boolean[numButtons];

        public RemoteController() {
            for (int i = 0; i < numButtons; i++) {
                buttons[i] = null;
                buttonPressed[i] = false;
            }
        }
        
        public void setCommand(int idx, Command command) {
            if (idx >= 0 && idx < numButtons) {
                buttons[idx] = command;
                buttonPressed[idx] = false;
            }
        }

        public void pressButton(int idx) {
            if (idx >= 0 && idx < numButtons && buttons[idx] != null) {
                if(buttonPressed[idx] == false) {
                    buttons[idx].execute();
                    buttonPressed[idx] = true;
                } else {
                    buttons[idx].undo();
                    buttonPressed[idx] = false;
                }
            }else {
                System.out.println("No command assigned to this button.");
            }
        }

    }


    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();
        
        RemoteController remote = new RemoteController();

        remote.setCommand(0, new LightCommand(livingRoomLight));
        remote.setCommand(1, new FanCommand(ceilingFan));

        System.out.println("Toggling Light: ");
        remote.pressButton(0); // Light ON
        remote.pressButton(0); // Light OFF

        System.out.println("\nToggling Fan: ");
        remote.pressButton(1); // Fan STARTED
        remote.pressButton(1); // Fan STOPPED

        System.out.println("\nPressing unassigned button: ");
        remote.pressButton(2); // No command assigned


    }
}
