package zoo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ActionTriggerTest {

    private static Zoo zoo;
    private static ActionTrigger trigger;

    private AnimalType carnivore = AnimalType.CARNIVORE;
    private AnimalType herbivore = AnimalType.HERBIVORE;

    @BeforeAll
    static void setup(){

        zoo = new Zoo();
        String filePath = ZooTest.class.getClassLoader().getResource("zooAnimals.json").getPath();
        zoo.addAnimalsXML(filePath);
        //DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("attempt4.xml");
        trigger = new ActionTrigger(zoo);
    }

    @Test
    void visit() {
        trigger.visit(herbivore);
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.MAKE_NOISE);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);

        trigger.visit(carnivore);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);
    }

    @Test
    void feedAnimals() {
        trigger.feedAnimals(carnivore);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.CALM);
        trigger.feedAnimals(herbivore);
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.CALM);
    }

    @Test
    void setNight() {
        zoo.setAllHerbivoreState(AnimalState.CALM);
        zoo.setAllCarnivoreState(AnimalState.CALM);
        trigger.setNight();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.SLEEP);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.SLEEP);

        zoo.setAllHerbivoreState(AnimalState.CALM);
        zoo.setAllCarnivoreState(AnimalState.MAKE_NOISE);
        trigger.setNight();
        assertNotEquals(zoo.getAllHerbivoreState(), AnimalState.SLEEP);
        assertNotEquals(zoo.getAllCarnivoreState(), AnimalState.SLEEP);
    }

    @Test
    void setMorning() {
        zoo.setAllHerbivoreState(AnimalState.SLEEP);
        zoo.setAllCarnivoreState(AnimalState.SLEEP);
        trigger.setMorning();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.CALM);
       assertEquals(zoo.getAllCarnivoreState(), AnimalState.CALM);

        zoo.setAllHerbivoreState(AnimalState.MAKE_NOISE);
        zoo.setAllCarnivoreState(AnimalState.CALM);
        trigger.setMorning();
        assertNotEquals(zoo.getAllHerbivoreState(), AnimalState.CALM);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.CALM);
    }

    @Test
    void setThunder() {
        trigger.setThunder();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.MAKE_NOISE);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);
    }

    @Test
    void setRain(){
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.SHUT_UP);
        trigger.setRain();

    }

    @Test
    void waterAnimals() {
        trigger.waterAnimal();
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);
        trigger.waterAnimal();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.SHUT_UP);

    }
}