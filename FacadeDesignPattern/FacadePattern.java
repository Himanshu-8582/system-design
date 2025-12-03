public class FacadePattern {

    public static class PowerSupply {
        public void providePower() {
            System.out.println("Providing power to the system.....");
        }
    }

    public static class CoolingSystem {
        public void coolSystem() {
            System.out.println("Fan Started : Cooling the system.....");
        }
    }

    public static class CPU {
        public void initialize() {
            System.out.println("CUP Initialization started .....");
        }
    }

    public static class Memory {
        public void setTest() {
            System.out.println("Memory Test started .....");
        }
    }

    public static class HardDrive {
        public void signUp() {
            System.out.println("Hard Drive: SignUp started .....");
        }
    }

    public static class OperatingSystem {
        public void load() {
            System.out.println("Operating System: Loading started .....");
        }
    }

    public static class BIOS {
        public void boot(CPU cpu, Memory memory) {
            System.out.println("BIOS: Booting CPU and Memory .....");
            cpu.initialize();
            memory.setTest();
        }
    }
    

    public static class ComputerFacade {                // Facade Class helps to decouple the client from complex subsystems
        private PowerSupply powerSupply;
        private CoolingSystem coolingSystem;
        private CPU cpu;
        private Memory memory;
        private HardDrive hardDrive;
        private OperatingSystem operatingSystem;
        private BIOS bios;

        public ComputerFacade() {
            this.powerSupply = new PowerSupply();
            this.coolingSystem = new CoolingSystem();
            this.cpu = new CPU();
            this.memory = new Memory();
            this.hardDrive = new HardDrive();
            this.operatingSystem = new OperatingSystem();
            this.bios = new BIOS();
        }

        public void startComputer() {
            System.out.println("-----Starting Computer-----");
            powerSupply.providePower();
            coolingSystem.coolSystem();
            bios.boot(cpu, memory);
            hardDrive.signUp();
            operatingSystem.load();
            System.out.println("Computer started successfully!");
        }
    }


    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.startComputer();
    }
}
