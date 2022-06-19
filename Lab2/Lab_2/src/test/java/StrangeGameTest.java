import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StrangeGameTest {
    private StrangeGame strangeGame = new StrangeGame();

    Hour hourStub = mock(Hour.class);
    Prison prisonSpy = spy(Prison.class);

    // If a notorious player enter the game on 0:00 - 11:59, verify that prison doesn’t do any imprisonment.
    @Test
    void test_a() throws InterruptedException{
        this.strangeGame.hour = hourStub;
        this.strangeGame.prison = prisonSpy;
        when(hourStub.getHour()).thenReturn(10);

        Player player = new Player();
        this.strangeGame.enterGame(player);
        verify(this.strangeGame.prison, never()).imprisonment(player);
    }

    // If a notorious player enter the game on 12:00 - 23:59, assert the output correct.
    @Test
    void test_b() throws InterruptedException {
        this.strangeGame.hour = hourStub;
        this.strangeGame.prison = prisonSpy;
        when(hourStub.getHour()).thenReturn(13);
        doNothing().when(prisonSpy).imprisonment(any(Player.class));

        Player player = new Player();
        int actual_reputation = player.getReputation();
        String actual_res = this.strangeGame.enterGame(player);

        assertTrue(actual_reputation < 0);
        verify(this.strangeGame.prison, times(1)).crime(player);
        assertEquals("After a long period of punishment, the player can leave! :)", actual_res);

    }

    //  Suppose 3 players go to the prison. Verify prisonerLog in prison will record prisoner’s playerid with spy method. Don’t stub getLog function.
    @Test
    void test_c() throws InterruptedException {
        this.strangeGame.hour = hourStub;
        this.strangeGame.prison = prisonSpy;
        when(hourStub.getHour()).thenReturn(15);
        doNothing().when(prisonSpy).imprisonment(any(Player.class));

        String id_1 = "123";
        String id_2 = "456";
        String id_3 = "789";
        Player player_1 = new Player(id_1, -1);
        Player player_2 = new Player(id_2, -1);
        Player player_3 = new Player(id_3, -1);
        this.strangeGame.enterGame(player_1);
        this.strangeGame.enterGame(player_2);
        this.strangeGame.enterGame(player_3);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add(id_1);
        expected.add(id_2);
        expected.add(id_3);

        verify(prisonSpy, times(3)).crime(any(Player.class));
        ArrayList actual_log = prisonSpy.getLog();
        assertEquals(expected, actual_log);
    }

    // Use stub method to test getScore function (PlayerID = your StudentID) to avoid connection to outer database.
    @Test
    void test_d() throws InterruptedException {
        class StrangeGameStub extends StrangeGame {
            @Override
            public int getScore(String playerid) {
                return 100;
            }
        }

        this.strangeGame = new StrangeGameStub();
        int actual_score = this.strangeGame.getScore("310551135");
        assertEquals(100, actual_score);
    }

    // Implement paypalService interface as a fake object to test donate function.
    @Test
    void test_e() throws InterruptedException {
        class MyPaypalService implements paypalService{

            @Override
            public String doDonate() {
                return "Success";
            }
        }
        MyPaypalService paypalService = new MyPaypalService();

        String actual_donate_result = this.strangeGame.donate(paypalService);
        assertEquals("Thank you", actual_donate_result);
    }
}