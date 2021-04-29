package zoo;


import java.text.Format;

public class Main {
        public static void main(String[] argv) {

                Zoo zoo = new Zoo();

                String filePath = null;
                String fileFormat = null;

                for(String arg : argv){
                        String[] args = arg.split("=");
                        switch (args[0]){
                                case "-configtype":
                                        fileFormat = args[1];
                                        break;
                                case "-configfile":
                                        filePath = args[1];
                                        break;
                        }
                }

                zoo.addAnimals(filePath, fileFormat);


                // Create user action trigger
                ActionTrigger trigger = new ActionTrigger(zoo);

                AnimalType herbivore = AnimalType.HERBIVORE;
                AnimalType carnivore = AnimalType.CARNIVORE;

                // All of the following actions are up to users choice
                zoo.printAllStates();
                trigger.setMorning();
                zoo.printAllStates();

                trigger.visit(herbivore);
                zoo.printAllStates();
                //       trigger.visit(carnivore);
                trigger.feedAnimals(herbivore);
                zoo.printAllStates();

                trigger.setNight();
                zoo.printAllStates();

                trigger.setMorning();
                zoo.printAllStates();

                trigger.setThunder();
                zoo.printAllStates();
                trigger.feedAnimals(carnivore);
                zoo.printAllStates();

                trigger.feedAnimals(herbivore);
                zoo.printAllStates();
                trigger.setNight();
                zoo.printAllStates();

                trigger.setMorning();
                zoo.printAllStates();

                trigger.setRain();
                zoo.printAllStates();

                trigger.waterAnimal();
                zoo.printAllStates();

        }
}