import com.kubadziworski.parser.domain.Class;
import com.kubadziworski.parser.parser.ListenerOrientedParser;
import com.kubadziworski.parser.parser.VisitorOrientedParser;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jdziworski on 30.03.16.
 */
@RunWith(JUnit4.class)
public class Test {

    private static final String someLangSourceCode =
            "class SomeClass {\n"+
            "    fun1 {\n"+
            "        instruction11\n"+
            "        instruction12\n"+
            "    }\n"+
            "    fun2 {\n"+
            "        instruction21\n"+
            "        instruction22\n"+
            "    }\n"+
            "}";


    @org.junit.Test
    public void bothParserImplementationsShouldGiveSameResult() throws Exception {
        final Class listenerOutput = new ListenerOrientedParser().parse(someLangSourceCode);
        final Class visitorOutput = new VisitorOrientedParser().parse(someLangSourceCode);
        assertThat(listenerOutput, is(visitorOutput));
    }
}
