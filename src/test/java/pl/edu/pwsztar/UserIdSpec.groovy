package pl.edu.pwsztar

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*
import spock.lang.Specification
import spock.lang.Unroll

class UserIdSpec extends Specification {

    @Unroll
    def "check if length is correct, the result is  #result" () {

        given: "create new user id"
        def userId = new UserId(id)

        when: "length is checked"
        def newResult = userId.isCorrectSize()
        then: "result"
        newResult == result
        where:
        id              | result
        "97050703977"     | true
        "9705056565"      | false
        "345345"          | false
        ""              | false
        "92342343255"     | true
        "siemajestem"   | true
        "siemaaaa"      | false
    }

    @Test
    void getSex() {
        // given
        def userId = new UserId("97050703977")
        def userId2 = new UserId("97050703967")
        def userId3 = new UserId("97050703957")
        def userId4 = new UserId("97050703947")
        // when
        Optional<UserIdChecker.Sex> sex = userId.getSex()
        Optional<UserIdChecker.Sex> sex2 = userId2.getSex()
        Optional<UserIdChecker.Sex> sex3 = userId3.getSex()
        Optional<UserIdChecker.Sex> sex4 = userId4.getSex()
        // then
        assertEquals(Optional.of(UserIdChecker.Sex.MAN), sex)
        assertEquals(Optional.of(UserIdChecker.Sex.WOMAN), sex2)
        assertEquals(Optional.of(UserIdChecker.Sex.MAN), sex3)
        assertEquals(Optional.of(UserIdChecker.Sex.WOMAN), sex4)
    }

    @Test
    void isCorrect() {
        // given
        def userId = new UserId("97050703977")
        def userId1 = new UserId("81122154688")
        def userId2 = new UserId("94082521489")
        def userId3 = new UserId("66080472715")
        def userId4 = new UserId("7583854")
        def userId5 = new UserId("88883483481")
        def userId6 = new UserId("652748324322234")
        // when
        def correct = userId.isCorrect()
        def correct1 = userId1.isCorrect()
        def correct2 = userId2.isCorrect()
        def correct3 = userId3.isCorrect()
        def correct4 = userId4.isCorrect()
        def correct5 = userId5.isCorrect()
        def correct6 = userId6.isCorrect()
        // then
        assertTrue(correct)
        assertTrue(correct1)
        assertTrue(correct2)
        assertTrue(correct3)
        assertFalse(correct4)
        assertFalse(correct5)
        assertFalse(correct6)
    }

    @Test
    void getDate(){
        //given
        def user1997 = new UserId("97050703977")
        def user1884= new UserId("84890602601")
        def user2019 = new  UserId("19272410608")
        def user2176 = new UserId("76500764919")
        def user2234 = new UserId("34672331719")
        //when
        def correct1 = user1997.getDate()
        def correct2 = user1884.getDate()
        def correct3 = user2019.getDate()
        def correct4 = user2176.getDate()
        def correct5 = user2234.getDate()
        //then
        assertEquals(Optional.of("07-05-1997"),correct1)
        assertEquals(Optional.of("06-09-1884"),correct2)
        assertEquals(Optional.of("24-07-2019"),correct3)
        assertEquals(Optional.of("07-10-2176"),correct4)
        assertEquals(Optional.of("23-07-2234"),correct5)
    }
}
