import org.junit.Assert;
import org.junit.Test;
import ua.nekitoss.model.heroes.Attacker;

public class AttackerTest {

  private static final String MESSAGE = "exp is calculated wrong";

  @Test
  public void testCalculateLvlByExp(){
    Attacker a = new Attacker();
    Assert.assertEquals(MESSAGE, 0, a.calculateLvlByExp(0));
    Assert.assertEquals(MESSAGE, 0, a.calculateLvlByExp(1));
    Assert.assertEquals(MESSAGE, 0, a.calculateLvlByExp(999));

    Assert.assertEquals(MESSAGE, 1, a.calculateLvlByExp(1000));
    Assert.assertEquals(MESSAGE, 1, a.calculateLvlByExp(1001));
    Assert.assertEquals(MESSAGE, 1, a.calculateLvlByExp(2449));

    Assert.assertEquals(MESSAGE, 2, a.calculateLvlByExp(2450));
    Assert.assertEquals(MESSAGE, 2, a.calculateLvlByExp(2451));
    Assert.assertEquals(MESSAGE, 2, a.calculateLvlByExp(4799));

    Assert.assertEquals(MESSAGE, 3, a.calculateLvlByExp(4800));
    Assert.assertEquals(MESSAGE, 3, a.calculateLvlByExp(4801));
    Assert.assertEquals(MESSAGE, 3, a.calculateLvlByExp(8049));

    Assert.assertEquals(MESSAGE, 4, a.calculateLvlByExp(8050));
    Assert.assertEquals(MESSAGE, 4, a.calculateLvlByExp(8051));
    Assert.assertEquals(MESSAGE, 4, a.calculateLvlByExp(12199));

    Assert.assertEquals(MESSAGE, 5, a.calculateLvlByExp(12200));
    Assert.assertEquals(MESSAGE, 5, a.calculateLvlByExp(12201));
    //Assert.assertEquals(MESSAGE, 0, a.calculateLvlByExp(0));




  }
}
