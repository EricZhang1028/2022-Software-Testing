import org.junit.jupiter.api.Assertions;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Test;

class BoundedQueueTest {

    BoundedQueue bq = new BoundedQueue(3);
    Object firstObject = new Object();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bq.enQueue(firstObject);
    }

    /*
     * 2 Tests for BoundedQueue method BoundedQueue(int capacity)
     * The two characteristics associated with BoundedQueue(int capacity) are: C1, C2
    */

    // Test 1 of BoundedQueue(int capacity): testBoundedQueue_BaseCase(): C1-T, C2-F
    @org.junit.jupiter.api.Test
    void testBoundedQueue_BaseCase() {
        BoundedQueue bq_test = new BoundedQueue(2);
    }

    // Test 2 of BoundedQueue(int capacity): testBoundedQueue_C2(): C1-T, C2-T
    @org.junit.jupiter.api.Test
    void testBoundedQueue_C2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BoundedQueue(-1);
        });
    }


    /*
     * 3 Tests for BoundedQueue method enQueue(Object o)
     * The four characteristics associated with enQueue(Object o) are C1, C3, C4, C7
    */

    // Test 1 of enQueue(Object o): testEnQueue_BaseCase(): C1-T, C3-T, C4-F, C7-F
    @org.junit.jupiter.api.Test
    void testEnQueue_BaseCase() {
        bq.enQueue(new Object());
    }

    // Test 2 of enQueue(Object o): testEnQueue_C3_C4(): C1-T, C3-F, C4-T, C7-F
    @org.junit.jupiter.api.Test
    void testEnQueue_C3_C4() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            bq.enQueue(null);
        });
    }

    // Test 3 of enQueue(Object o): testEnQueue_C3_C7(): C1-T, C3-F, C4-F, C7-T
    @org.junit.jupiter.api.Test
    void testEnQueue_C3_C7() {
        bq.enQueue(new Object());
        bq.enQueue(new Object());

        // enQueue when bq is full
        Assertions.assertThrows(IllegalStateException.class, () -> {
            bq.enQueue(new Object());
        });
    }


    /*
     * 2 Tests for BoundedQueue method deQueue()
     * The three characteristics associated with deQueue() are C1, C5, C6
    */

    // Test 1 of deQueue(): testDeQueue_BaseCase(): C1-T, C5-T, C6-F
    @org.junit.jupiter.api.Test
    void testDeQueue_BaseCase() {
        Object old = bq.deQueue();
    }

    // Test 2 of deQueue(): testDeQueue_C5_C6(): C1-T, C5-F, C6-T
    @org.junit.jupiter.api.Test
    void testDeQueue_C5_C6() {
        // let bq empty
        bq.deQueue();

        // remove from empty bq
        Assertions.assertThrows(IllegalStateException.class, () -> {
            bq.deQueue();
        });
    }


    /*
     * 2 Tests for BoundedQueue method isEmpty()
     * The two characteristics associated with isEmpty() are C1, C6
     */

    // Test 1 of isEmpty(): testIsEmpty_BaseCase(): C1-T, C6-F
    @org.junit.jupiter.api.Test
    void testIsEmpty_BaseCase() {
        Assertions.assertFalse(bq.isEmpty());
    }

    // Test 2 of isEmpty(): testIsEmpty_C6(): C1-T, C6-T
    @org.junit.jupiter.api.Test
    void testIsEmpty_C6() {
        // let bq empty
        bq.deQueue();

        Assertions.assertTrue(bq.isEmpty());
    }


    /*
     * 2 Tests for BoundedQueue method isFull()
     * The two characteristics associated with isFull() are C1, C7
     */

    // Test 1 of isFull(): testIsFull_BaseCase(): C1-T, C7-F
    @org.junit.jupiter.api.Test
    void testIsFull_BaseCase() {
        Assertions.assertFalse(bq.isFull());
    }

    // Test 2 of isFull(): testIsFull_C7(): C1-T, C7-T
    @org.junit.jupiter.api.Test
    void testIsFull_C7() {
        // let bq full
        bq.enQueue(new Object());
        bq.enQueue(new Object());

        Assertions.assertTrue(bq.isFull());
    }
}