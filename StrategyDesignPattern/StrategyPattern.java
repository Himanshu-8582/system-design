package StrategyDesignPattern;

public class StrategyPattern {

    public interface WalkableRobot {
        void walk();
    }
    
    public class NormalWalk implements WalkableRobot {
        public void walk() {
            System.out.println("Walking normally");
        }
    }

    public class NoWalk implements WalkableRobot {
        public void walk() {
            System.out.println("Can't walk");
        }
    }






    public interface TalkableRobot {
        void talk();
    }

    public class NormalTalk implements TalkableRobot {
        public void talk() {
            System.out.println("Talking normally");
        }
    }

    public class NoTalk implements TalkableRobot {
        public void talk() {
            System.out.println("Can't talk");
        }
    }




    public interface FlyableRobot {
        void fly();
    }

    public class NormalFly implements FlyableRobot {
        public void fly() {
            System.out.println("Flying normally");
        }
    }

    public class NoFly implements FlyableRobot {
        public void fly() {
            System.out.println("Can't fly");
        }
    }
    



    // Dumb Class
    public class Robot {
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

    public class CompanionRobot extends Robot {
        CompanionRobot(WalkableRobot w, TalkableRobot t, FlyableRobot f) {
            super(w, t, f);
        }

        void projection() {
            System.out.println("Displaying friendly companion features...");
        }
    }

    public static void main(String[] args) {
        
    }
}
