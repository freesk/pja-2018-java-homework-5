package mypackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ VehicleTest.class, BusTest.class, ScopeTest.class })
public class AllTests {

}
