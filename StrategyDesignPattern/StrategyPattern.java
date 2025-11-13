public class StrategyPattern {

    public interface WalkableRobot {
        void walk();
    }
    
    public static class NormalWalk implements WalkableRobot {
        public void walk() {
            System.out.println("Walking normally");
        }
    }

    public static class NoWalk implements WalkableRobot {
        public void walk() {
            System.out.println("Can't walk");
        }
    }






    public interface TalkableRobot {
        void talk();
    }

    public static class NormalTalk implements TalkableRobot {
        public void talk() {
            System.out.println("Talking normally");
        }
    }

    public static class NoTalk implements TalkableRobot {
        public void talk() {
            System.out.println("Can't talk");
        }
    }




    public interface FlyableRobot {
        void fly();
    }

    public static class NormalFly implements FlyableRobot {
        public void fly() {
            System.out.println("Flying normally");
        }
    }

    public static class NoFly implements FlyableRobot {
        public void fly() {
            System.out.println("Can't fly");
        }
    }
    



    // Dumb Class
    public static class Robot {
        WalkableRobot walkableBehaviour;
        TalkableRobot talkableBehaviour;
        FlyableRobot flyableBehaviour;

        Robot(WalkableRobot w, TalkableRobot t, FlyableRobot f) {
            this.walkableBehaviour = w;
            this.talkableBehaviour = t;
            this.flyableBehaviour = f;
        }

        void walk() {
            walkableBehaviour.walk();
        }

        void talk() {
            talkableBehaviour.talk();
        }

        void fly() {
            flyableBehaviour.fly();
        }

        void projection() {
        }

    }

    public static class CompanionRobot extends Robot {
        CompanionRobot(WalkableRobot w, TalkableRobot t, FlyableRobot f) {
            super(w, t, f);
        }

        void projection() {
            System.out.println("Displaying friendly companion features...");
        }
    }

    public static class WorkerRobot extends Robot {
        WorkerRobot(WalkableRobot w, TalkableRobot t, FlyableRobot f) {
            super(w, t, f);
        }

        void projection() {
            System.out.println("Displaying worker effective status...");
        }
    }

    public static void main(String[] args) {
        Robot robot = new CompanionRobot(new NormalWalk(), new NormalTalk(), new NormalFly());
        robot.walk();
        robot.talk();
        robot.fly();
        robot.projection();
        System.out.println("-------------------------------------------------");
        Robot robot2 = new WorkerRobot(new NoWalk(), new NoTalk(), new NoFly());
        robot2.walk();
        robot2.talk();
        robot2.fly();
        robot2.projection();
    }
}
