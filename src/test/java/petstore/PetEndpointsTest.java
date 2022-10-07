package petstore;

import org.testng.annotations.Test;
import petstore.entities.Pet;

import java.io.IOException;

public class PetEndpointsTest {
    @Test
    public void petTest() throws IOException {
        new PetEndpoints().getPetById(111);
    }

    @Test
    public void createPetTest() throws IOException {
        new PetEndpoints().createPet();
    }

    @Test
    public void createPetJSONTest() throws IOException {
        String pet = new PetEndpoints().createPetJSON("testname", "1");
        System.out.println(pet);

    }

    @Test
    public void createPetGsonTest() throws IOException {
        PetEndpoints petEndpoints = new PetEndpoints();
        Pet dog = new Pet();
        dog.setName("test");
        dog.setStatus("sold");
        Pet newDog = petEndpoints.createPetGson(dog);
        System.out.println(newDog);

    }
}
