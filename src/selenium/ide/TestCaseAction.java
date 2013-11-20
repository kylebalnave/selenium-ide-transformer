/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package selenium.ide;

/**
 *
 * @author kyleb2
 */
public class TestCaseAction {
    
    String name;
    String param1;
    String param2;
    
    public TestCaseAction(String name, String param1, String param2) {
        this.name = name;
        this.param1 = param1;
        this.param2 = param2;
    }

    public String getName() {
        return name;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }
    
    
    
}
