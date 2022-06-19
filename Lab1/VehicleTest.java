import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();
        vehicle2 = new Vehicle(5, "south");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        vehicle1.finalize();
        vehicle2.finalize();
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        vehicle1.finalize();
        assertEquals(1, Vehicle.totalVehicle());
        vehicle1 = new Vehicle();

        vehicle2.finalize();
        assertEquals(1, Vehicle.totalVehicle());
        vehicle2 = new Vehicle(5, "south");
    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        int newSpeed = 100;
        vehicle1.setSpeed(newSpeed);
        assertEquals(newSpeed, vehicle1.getSpeed());

        vehicle2.setSpeed(newSpeed);
        assertEquals(newSpeed, vehicle2.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setDir() {
        String newDir = "west";
        vehicle1.setDir(newDir);
        assertEquals(newDir, vehicle1.getDir());

        vehicle2.setDir(newDir);
        assertEquals(newDir, vehicle2.getDir());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        assertEquals(0, vehicle1.getSpeed());
        assertEquals(5, vehicle2.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        assertEquals("north", vehicle1.getDir());
        assertEquals("south", vehicle2.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        assertEquals(2, Vehicle.totalVehicle());
    }
}