package Utility;
import Members.Member;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class memberHandlerTest {

    @Before
    public void setUp() {
        memberHandler.memberList = new ArrayList<>();
    }

    @Test
    public void testRevenue() {
        Member member1 = new Member(1,"John Doe", LocalDate.of(2006, 4, 1), true, false);
        Member member2 = new Member(2,"Emma Pedersen", LocalDate.of(1955, 5, 1), false, true);
        Member member3 = new Member(3,"James Smith", LocalDate.of(2000, 6, 23), false, true);

        memberHandler.memberList.add(member1);
        memberHandler.memberList.add(member2);
        memberHandler.memberList.add(member3);

        int expectedRevenue = 1500;

        int actualRevenue = memberHandler.revenue();

        assertEquals(expectedRevenue, actualRevenue);
    }
}
